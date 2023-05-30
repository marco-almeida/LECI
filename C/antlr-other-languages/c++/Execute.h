#pragma once

#include <unordered_map>
#include "CalculatorBaseVisitor.h"

class Execute : public CalculatorBaseVisitor {
public:

   //virtual std::any visitMain(CalculatorParser::MainContext *ctx) override;

   virtual std::any visitShow(CalculatorParser::ShowContext *ctx) override;

   virtual std::any visitAssignment(CalculatorParser::AssignmentContext *ctx) override;

   virtual std::any visitExprUnaryOp(CalculatorParser::ExprUnaryOpContext *ctx) override;

   virtual std::any visitExprMultDiv(CalculatorParser::ExprMultDivContext *ctx) override;

   virtual std::any visitExprAddSub(CalculatorParser::ExprAddSubContext *ctx) override;

   virtual std::any visitExprParent(CalculatorParser::ExprParentContext *ctx) override;

   virtual std::any visitExprID(CalculatorParser::ExprIDContext *ctx) override;

   virtual std::any visitExprNumber(CalculatorParser::ExprNumberContext *ctx) override;

private:
   std::unordered_map<std::string, double> variables;
};
