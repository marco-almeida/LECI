/**
 * Crie o módulo LeakyQueue de forma que este programa funcione devidamente
 * respeitando o comportamento esperado de uma Fila.
 */

import static java.lang.System.*;

public class ProgX {
  static final int N = 3;
  public static void main(String[] args) {
    LeakyQueue fr = new LeakyQueue(N); // cria uma fila rota com um máximo de N elementos

    for (int i=0; i<args.length; i++) {
      try { // tenta converter argumento para número real
        double num = Double.valueOf(args[i]);
        fr.in(num); // adiciona elemento à fila (caso esteja cheia deita fora o mais antigo)
      }
      catch (NumberFormatException e) { // o argumento não era um número real
        if (args[i].equals("-")) {
          if (!fr.isEmpty()) // vê se a fila não está vazia
            fr.out(); // retira elemento da fila
        }
        // ignora outro qualquer argumento
      }
      out.printf("i = %-3d", i);
      fr.print(); // escreve conteúdo da fila numa única linha
      if (!fr.isEmpty()) {
        for(int j = fr.size();j < N;j++)
          out.printf(" %4c", ' ');
        double m = fr.minimum(); // Mínimo dos números da fila
        out.printf("    (Min = %1.1f)", m);
      }
      out.println();
    }

    while(!fr.isEmpty()) {
      fr.out();
    }
    assert fr.size() == 0;
  }
}

/* Exemplos de utilização e resultados esperados:

$ java -ea ProgX 1 2 3 4 5 6
i = 0    1.0              (Min = 1.0)
i = 1    1.0  2.0         (Min = 1.0)
i = 2    1.0  2.0  3.0    (Min = 1.0)
i = 3    2.0  3.0  4.0    (Min = 2.0)
i = 4    3.0  4.0  5.0    (Min = 3.0)
i = 5    4.0  5.0  6.0    (Min = 4.0)

$ java -ea ProgX 9 8 7 6 5 4 3 2 1
i = 0    9.0              (Min = 9.0)
i = 1    9.0  8.0         (Min = 8.0)
i = 2    9.0  8.0  7.0    (Min = 7.0)
i = 3    8.0  7.0  6.0    (Min = 6.0)
i = 4    7.0  6.0  5.0    (Min = 5.0)
i = 5    6.0  5.0  4.0    (Min = 4.0)
i = 6    5.0  4.0  3.0    (Min = 3.0)
i = 7    4.0  3.0  2.0    (Min = 2.0)
i = 8    3.0  2.0  1.0    (Min = 1.0)

$ java -ea ProgX 1 3 - 5 7 - 9 11 -
i = 0    1.0              (Min = 1.0)
i = 1    1.0  3.0         (Min = 1.0)
i = 2    3.0              (Min = 3.0)
i = 3    3.0  5.0         (Min = 3.0)
i = 4    3.0  5.0  7.0    (Min = 3.0)
i = 5    5.0  7.0         (Min = 5.0)
i = 6    5.0  7.0  9.0    (Min = 5.0)
i = 7    7.0  9.0 11.0    (Min = 7.0)
i = 8    9.0 11.0         (Min = 9.0)

$ java -ea ProgX 2 - - 4 - 6 8
i = 0    2.0              (Min = 2.0)
i = 1
i = 2
i = 3    4.0              (Min = 4.0)
i = 4
i = 5    6.0              (Min = 6.0)
i = 6    6.0  8.0         (Min = 6.0)

*/

