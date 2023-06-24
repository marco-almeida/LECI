#include <iostream>
#include <antlr4-runtime.h>
#include "CalculatorLexer.h"
#include "CalculatorParser.h"
#include "Execute.h"

using namespace std;
using namespace antlr4;
using namespace antlr4::tree;

int main(int argc, const char* argv[]) {
   std::istream *stream = &cin;
   string line;
   int lineNum = 1;
   CalculatorParser* parser = new CalculatorParser(NULL);
   // replace error listener:
   //parser->removeErrorListeners(); // remove ConsoleErrorListener
   //parser->addErrorListener(new ErrorHandlingListener());
   Execute* visitor0 = new Execute();
   while(getline(*stream, line)) {
      // create a ANTLRInputStream that reads from standard input:
      ANTLRInputStream* input;
      std::stringstream sstream;
      sstream << line << "\n";
      std::istream *linestream = &sstream;
      input = new ANTLRInputStream(*linestream);
      // create a lexer that feeds off of input ANTLRInputStream:
      CalculatorLexer* lexer = new CalculatorLexer(input);
      lexer->setLine(lineNum);
      lexer->setCharPositionInLine(0);
      // create a buffer of tokens pulled from the lexer:
      CommonTokenStream* tokens = new CommonTokenStream(lexer);
      // create a parser that feeds off the tokens buffer:
      parser->setInputStream(tokens);
      // begin parsing at main rule:
      ParseTree* tree = parser->main();
      if (parser->getNumberOfSyntaxErrors() == 0) {
         // print LISP-style tree:
         // cout << tree->toStringTree(parser) << endl;
         visitor0->visit(tree);
      }
      lineNum++;
   }
}
