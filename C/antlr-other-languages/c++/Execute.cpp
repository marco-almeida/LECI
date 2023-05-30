#include "Execute.h"

//std::any Execute::visitMain(CalculatorParser::MainContext *ctx) { // not necessary
//   std::any res;
//
//   for(CalculatorParser::StatContext* stat: ctx->stat()) {
//      res = visit(stat);
//   }
//
//   return res;
//}

std::any Execute::visitShow(CalculatorParser::ShowContext *ctx) {
   std::any res = visit(ctx->expr());
   double r;
   if (res.has_value()) {
      r = std::any_cast<double>(res);
      printf("Result: %lg\n", r);
   }
   return res;
}

std::any Execute::visitAssignment(CalculatorParser::AssignmentContext *ctx) {
   std::any res = visit(ctx->expr());
   if (res.has_value()) {
      std::string id = ctx->ID()->getText();
      variables[id] = std::any_cast<double>(res);
   }
   return res;
}

std::any Execute::visitExprUnaryOp(CalculatorParser::ExprUnaryOpContext *ctx) {
   std::any res = visit(ctx->expr());
   if (res.has_value() && ctx->op->getText()[0] == '-') {
      res = -std::any_cast<double>(res);
   }
   return res;
}

std::any Execute::visitExprMultDiv(CalculatorParser::ExprMultDivContext *ctx) {
   std::any res;
   std::any e1 = visit(ctx->expr(0));
   std::any e2 = visit(ctx->expr(1));
   if (e1.has_value() && e2.has_value()) {
      switch(ctx->op->getText().c_str()[0]) {
         case '*':
            res = std::any_cast<double>(e1) * std::any_cast<double>(e2);
            break;
         case '/':
            if (std::any_cast<double>(e2) == 0)
               fprintf(stderr,"ERROR: divide by zero!\n");
            else
               res = std::any_cast<double>(e1) / std::any_cast<double>(e2);
            break;
      }
   }
   return res;
}

std::any Execute::visitExprAddSub(CalculatorParser::ExprAddSubContext *ctx) {
   std::any res;
   std::any e1 = visit(ctx->expr(0));
   std::any e2 = visit(ctx->expr(1));
   if (e1.has_value() && e2.has_value()) {
      switch(ctx->op->getText().c_str()[0]) {
         case '+':
            res = std::any_cast<double>(e1) + std::any_cast<double>(e2);
            break;
         case '-':
            res = std::any_cast<double>(e1) - std::any_cast<double>(e2);
            break;
      }
   }
   return res;
}

std::any Execute::visitExprParent(CalculatorParser::ExprParentContext *ctx) {
   return visit(ctx->expr());
}

std::any Execute::visitExprID(CalculatorParser::ExprIDContext *ctx) {
   std::any res;
   std::string id = ctx->ID()->getText();
   if (variables.find(id) == variables.end())
      fprintf(stderr,"ERROR: variable \"%s\" not found!\n", id.c_str());
   else
      res = variables[id];
   return res;
}

std::any Execute::visitExprNumber(CalculatorParser::ExprNumberContext *ctx) {
   return atof(ctx->Number()->getText().c_str());
}

