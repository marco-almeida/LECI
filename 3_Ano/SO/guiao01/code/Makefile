CC = g++
CXXFLAGS = -Wall -ggdb -pthread

LDFLAGS = -pthread -lm

TARGET = ull-main

all:	$(TARGET)

$(TARGET):	main.cpp ull.cpp 
		g++ -o $@ $^ $(LDFLAGS)

$(TARGET):	ull.h

clean:
		rm -f *.o *~

cleanall:	clean
		rm -f $(TARGET)

