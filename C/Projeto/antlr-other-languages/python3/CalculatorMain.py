import sys
from antlr4 import *
from CalculatorLexer import CalculatorLexer
from CalculatorParser import CalculatorParser
from Execute import Execute

def main(argv):
   visitor0 = Execute()
   for line in sys.stdin:
      input_stream = InputStream(line)
      lexer = CalculatorLexer(input_stream)
      stream = CommonTokenStream(lexer)
      parser = CalculatorParser(stream)
      tree = parser.main()
      if parser.getNumberOfSyntaxErrors() == 0:
         visitor0.visit(tree)

if __name__ == '__main__':
      main(sys.argv)
