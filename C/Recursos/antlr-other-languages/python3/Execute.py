from antlr4 import *
from CalculatorParser import CalculatorParser
from CalculatorVisitor import CalculatorVisitor

class Execute(CalculatorVisitor):
   def visitShow(self, ctx:CalculatorParser.ShowContext):
      res = self.visit(ctx.expr())
      if not res is None:
         print("Result: "+str(res))

   def visitAssignment(self, ctx:CalculatorParser.AssignmentContext):
      res = self.visit(ctx.expr())
      if not res is None:
         id = ctx.ID().getText()
         self.variables[id] = res

   def visitExprUnaryOp(self, ctx:CalculatorParser.ExprUnaryOpContext):
      res = self.visit(ctx.expr())
      if not res is None:
         if ctx.op.text == "-":
            res = -res
         return res

   def visitExprMultDiv(self, ctx:CalculatorParser.ExprMultDivContext):
      e1 = self.visit(ctx.expr(0))
      e2 = self.visit(ctx.expr(1))
      if (not e1 is None) and (not e2 is None):
         if ctx.op.text == "*":
            return e1 * e2
         else:
            if e2 == 0:
               print("ERROR: divide by zero!")
            else:
               return e1 / e2

   def visitExprAddSub(self, ctx:CalculatorParser.ExprAddSubContext):
      e1 = self.visit(ctx.expr(0))
      e2 = self.visit(ctx.expr(1))
      if (not e1 is None) and (not e2 is None):
         if ctx.op.text == "+":
            return e1 + e2
         else:
            return e1 - e2

   def visitExprParent(self, ctx:CalculatorParser.ExprParentContext):
      return self.visit(ctx.expr())

   def visitExprID(self, ctx:CalculatorParser.ExprIDContext):
      id = ctx.ID().getText()
      if id in self.variables:
         return self.variables[id]
      else:
         print("ERROR: variable \""+id+"\" not found!")

   def visitExprNumber(self, ctx:CalculatorParser.ExprNumberContext):
      return float(ctx.Number().getText())

   def __init__(self):
      self.variables = {}

