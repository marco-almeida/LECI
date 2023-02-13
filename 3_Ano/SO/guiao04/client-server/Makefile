CXXFLAGS = -Wall -ggdb -pthread -g

LDFLAGS = -lpthread -lm

TARGETS = client-server

all:	$(TARGETS)

client-server:	client-server.o service.o buffer.o fifo.o thread.o  utils.o
		g++ -o $@ $^ $(LDFLAGS)


buffer.o fifo.o thread.o service.o service.o utils.o: %.o:		%.h

clean:
		rm -f *.o *~

cleanall:	clean
		rm -f $(TARGETS)