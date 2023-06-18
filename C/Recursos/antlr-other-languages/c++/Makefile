.PHONY: all clean cleanall #install

GRAMMAR=Calculator.g4
TARGETS_CPP=CalculatorLexer.cpp CalculatorParser.cpp CalculatorBaseVisitor.cpp CalculatorVisitor.cpp Execute.cpp
OBJS=$(TARGETS_CPP:cpp=o) CalculatorMain.o
TARGET=CalculatorMain

CFLAGS=-Wall -ggdb -pthread -std=gnu++17 -I/usr/local/include/antlr4-runtime
LDFLAGS=-lantlr4-runtime

all: $(TARGET)

$(TARGETS_CPP): $(GRAMMAR)
	antlr4 -cpp -visitor $(GRAMMAR)

%.o: %.cpp
	g++ $(CFLAGS) -c $<

$(TARGET): $(GRAMMAR) $(TARGETS_CPP) $(OBJS)
	g++ $(CFLAGS) $(OBJS) $(LDFLAGS) -o $(TARGET)

#install: $(TARGET)
#	cp -a $(TARGET) ../../bin

clean:
	rm -fv $(OBJS) core

cleanall: clean
	antlr4-clean -cpp
	rm -fv $(TARGET)
