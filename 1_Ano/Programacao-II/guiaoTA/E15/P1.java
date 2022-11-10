/**
Este programa gere uma fila de espera que comprime valores repetidos à entrada.

Modifique o módulo de forma que o programa compile e funcione como desejado.
*/

import static java.lang.System.*;

public class P1 {
  public static void main(String[] args) {
    CompressedQueue queue = new CompressedQueue(); // cria uma fila vazia

    for (int a = 0; a < args.length; a++) {
      String name = args[a];
      if (isNumber(name)) {
        out.println("IN "+name);
        queue.in(Integer.parseInt(name)); // adiciona um valor ao fim da fila
      }
      else if (name.equalsIgnoreCase("out")) {
        if (queue.empty()) // queue vazia?
          err.println("ERROR: 1 or more elements are required!");
        else
        {
          out.println("OUT: ["+queue.peek()+":"+queue.peekCount()+"]"); // devolve valor e repetições do elemento que está na frente da fila
          queue.out(); // remove elemento que está na frente da fila
        }
      }
      else if (name.equalsIgnoreCase("show")) {
        out.println("QUEUE: "+queue.toString()); // devolve o conteúdo da fila na forma de String
      }
      else if (name.equalsIgnoreCase("max")) {
        if (queue.size() < 2) // número de elementos da fila
          err.println("ERROR: 2 or more elements are required!");
        else
          out.println("MAX: "+queue.maxDiff()); // diferença máxima entre elementos consecutivos na fila (valor absoluto)
      }
      else if (name.equalsIgnoreCase("min")) {
        if (queue.size() < 2)
          err.println("ERROR: 2 or more elements are required!");
        else
          out.println("MIN: "+queue.minDiff()); // diferença mínima entre elementos consecutivos na fila (valor absoluto)
      }
      else if (name.equals("clear")) {
        out.println("CLEAR");
        queue.clear();  // esvazia a fila
        assert queue.empty();  // verifica se a fila está vazia
      }
      else
        err.println("ERROR: invalid argument!");
    }
    out.println("QUEUE: "+queue.toString());
  }

  // Checks if string s contains an integer.
  public static boolean isNumber(String s) {
    boolean r = true;
    try {
      int n = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      r = false;
    }
    return r;
  }
}

