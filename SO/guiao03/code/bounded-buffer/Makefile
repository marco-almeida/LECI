CXXFLAGS = -Wall -ggdb -pthread

LDFLAGS = -lpthread -lm

TARGETS = bounded_buffer_unsafe bounded_buffer_safe

all:	$(TARGETS)

bounded_buffer_unsafe:	fifo_unsafe.o bounded_buffer.o delays.o process.o utils.o
		g++ -o $@ $^ $(LDFLAGS)

bounded_buffer_safe:	fifo_safe.o bounded_buffer.o delays.o process.o utils.o
		g++ -o $@ $^ $(LDFLAGS)

server-client:	fifo_strings.o server-client.o delays.o process.o utils.o
		g++ -o $@ $^ $(LDFLAGS)

fifo_strings.o:	%.o:	%.cpp fifo_strings.h	delays.h

fifo_unsafe.o fifo_safe.o:	%.o:	%.cpp fifo.h delays.h

delay.o:	%.o:	%.h

utils.o process.o:	%.o:	%.h dbc.h utils.h

clean:
		rm -f *.o *~

cleanall:	clean
		rm -f $(TARGETS)
