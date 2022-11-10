/**
 * Este programa serve para verificar se uma expressão aritmética (formada
 * por dígitos, operações elementares e parêntesis) é sintacticamente válida.
 * Crie o módulo StackX de forma que este programa funcione devidamente
 * respeitando o comportamento esperado de uma Pilha.
 */

import static java.lang.System.*;
import java.util.Scanner;

public class ProgX {
  public static void main(String[] args) {
    StackX p = new StackX();
    String[] e = {"(e)","e+e","e-e","e*e","e/e","D"}; // formas alternativas para
    // definir uma expressão
    if (args.length != 1) {
      exit(1); // ERRO na chamada!
    }

    String expr = args[0];
    for(int i = 0;i < expr.length();i++) {
      char c = expr.charAt(i);
      if (Character.isDigit(c))
        p.push('D'); // coloca o carácter 'D' no stack, indicando presença de um dígito
      else
        p.push(c); // coloca o carácter armazenado em c no stack

      out.print("  PUSH: ");
      out.println(p.toString()); // escreve numa única linha o conteúdo da pilha (sem a alterar)

      boolean change;
      do {
        // tenta reduzir a expressão no topo do stack para outra mais simples
        change = false;
        for(int j = 0;!change && j < e.length;j++) {
          String pattern = e[j];
          int n = pattern.length();
          if (p.topMatches(pattern)) { // verifica se os n caracteres no topo da pilha são iguais a pattern
            p.popN(n); // retira n elementos do topo
            p.push('e'); // coloca 'e' no stack
            change = true;
            out.print("REDUCE: ");
            out.println(p.toString()); // escreve numa única linha o conteúdo da pilha (sem a alterar)
          }
        }
      }
      while(change);
    }

    if (p.size() == 1 && p.top() == 'e')
      out.println("Correct expression!");
    else
      out.println("Bad expression!");
  }
}

/* Exemplos de utilização e resultados esperados:

$ java -ea ProgX "2+2"
  PUSH: D
REDUCE: e
  PUSH: e+
  PUSH: e+D
REDUCE: e+e
REDUCE: e
Correct expression!

$ java -ea ProgX "2+(2-3)"
  PUSH: D
REDUCE: e
  PUSH: e+
  PUSH: e+(
  PUSH: e+(D
REDUCE: e+(e
  PUSH: e+(e-
  PUSH: e+(e-D
REDUCE: e+(e-e
REDUCE: e+(e
  PUSH: e+(e)
REDUCE: e+e
REDUCE: e
Correct expression!

$ java -ea ProgX "3*(4/(3))"
  PUSH: D
REDUCE: e
  PUSH: e*
  PUSH: e*(
  PUSH: e*(D
REDUCE: e*(e
  PUSH: e*(e/
  PUSH: e*(e/(
  PUSH: e*(e/(D
REDUCE: e*(e/(e
  PUSH: e*(e/(e)
REDUCE: e*(e/e
REDUCE: e*(e
  PUSH: e*(e)
REDUCE: e*e
REDUCE: e
Correct expression!

$ java -ea ProgX "2+"
  PUSH: D
REDUCE: e
  PUSH: e+
Bad expression!

$ java -ea ProgX "(3*(2+4)+5))"
  PUSH: (
  PUSH: (D
REDUCE: (e
  PUSH: (e*
  PUSH: (e*(
  PUSH: (e*(D
REDUCE: (e*(e
  PUSH: (e*(e+
  PUSH: (e*(e+D
REDUCE: (e*(e+e
REDUCE: (e*(e
  PUSH: (e*(e)
REDUCE: (e*e
REDUCE: (e
  PUSH: (e+
  PUSH: (e+D
REDUCE: (e+e
REDUCE: (e
  PUSH: (e)
REDUCE: e
  PUSH: e)
Bad expression!

$ java -ea ProgX "2+4*(4++5)"
  PUSH: D
REDUCE: e
  PUSH: e+
  PUSH: e+D
REDUCE: e+e
REDUCE: e
  PUSH: e*
  PUSH: e*(
  PUSH: e*(D
REDUCE: e*(e
  PUSH: e*(e+
  PUSH: e*(e++
  PUSH: e*(e++D
REDUCE: e*(e++e
  PUSH: e*(e++e)
Bad expression!

*/

