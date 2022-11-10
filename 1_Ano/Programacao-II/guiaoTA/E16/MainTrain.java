/**
Este programa demonstra a utilização de de uma estrutura de dados para
gerir a carga e descarga de vagões num comboio de mercadorias.
Crie o módulo Train de forma que o programa compile e funcione devidamente.
**/

import static java.lang.System.*;

public class MainTrain {
  public static void main(String[] args) {
    if (args.length < 2 || !isNumber(args[0]) || !isNumber(args[1])) {
      err.printf("Usage: java -ea MainTrain WagonCapacity TrainCapacity [...]\n");
      exit(1);
    }
    double wagonCapacity = Double.parseDouble(args[0]);  // capacidade de cada vagão
    double trainCapacity = Double.parseDouble(args[1]);  // capacidade total do comboio
    if (wagonCapacity <= 0) {
      err.printf("ERRO: A capacidade tem de ser positiva!\n");
      exit(1);
    }
    if (trainCapacity < wagonCapacity) {
      err.printf("ERRO: A capacidade do comboio deve superar a de um vagão!\n");
      exit(1);
    }

    // Cria comboio com as capacidades indicadas:
    Train train = new Train(wagonCapacity, trainCapacity);
    out.printf("(Capacidade dos vagões: %.1f ton.)\n", wagonCapacity);
    out.printf("(Capacidade do comboio: %.1f ton.)\n", trainCapacity);
    double c;
    int n;

    for (int i = 2; i < args.length; i++) {
      out.printf("args[%d]=\"%s\": ", i, args[i]);

      if (args[i].equalsIgnoreCase("R")) {  // "R" => retirar um vagão
        if (train.isEmpty()) {  // se comboio não tem vagões, não posso retirar nada!
          err.println("ERRO: Comboio vazio!\n");
          exit(1);
        }
        c = train.removeWagon();   // retira 1 vagão e devolve a carga que transportava
        out.printf("Retira vagão com %.1f ton\n", c);
      } else if (isNumber(args[i])) {
        c = Double.parseDouble(args[i]);
        if (c >= 0) {  // valor positivo => acrescentar um vagão com essa carga
          if (c > wagonCapacity) {  // se carga supera capacidade de um vagão,
            err.printf("ERRO: Sobrecarga de vagão!\n");
            exit(1);
          }
          if (!train.acceptsCargo(c)) {  // se carga tornaria o comboio pesado demais,
            err.printf("ERRO: Sobrecarga do comboio!\n");
            exit(1);
          }
          train.addWagon(c);  // acrescenta ao comboio um novo vagão com carga c
          out.printf("Junta vagão com %.1f ton\n", c);
        } else {  // valor negativo => descarregar essa carga
          c = -c;
          if (c > train.totalCargo()) {  // não podemos descarregar mais do que temos!
            err.printf("ERRO: Comboio não tem carga suficiente!\n");
            exit(1);
          }
          n = train.unload(c);  // descarregar carga c, retirando n vagões vazios
          out.printf("Descarrega %.1f ton e retira %d vagões vazios.\n", c, n);
        }
      } else {
        err.printf("ERRO: Argumento inválido!\n");
        exit(1);
      }

      // Mostra o estado do comboio: tamanho, carga total e lista dos vagões
      out.printf("  (%d vagões, %.1f ton):\tLocO",
          train.size(), train.totalCargo());
      double[] wagons = train.list();  // obtém array com as cargas dos vagões
      for (int k = 0; k < wagons.length; k++) {
        out.printf("_[%.1f]", wagons[k]);
      }
      out.println();
    }
  }

  /** Verifica se string s contém um número real. **/
  public static boolean isNumber(String s) {
    boolean b = true;
    try {
      Double.parseDouble(s);
    } catch (NumberFormatException e) {
      b = false;
    }
    return b;
  }
}

/* EXEMPLOS de utilização e resultados esperados:

$ java -ea MainTrain 10 100 1 2 3 R R 4.5 0.1
(Capacidade dos vagões: 10.0 ton.)
(Capacidade do comboio: 100.0 ton.)
args[2]="1": Junta vagão com 1.0 ton
  (1 vagões, 1.0 ton):	LocO_[1.0]
args[3]="2": Junta vagão com 2.0 ton
  (2 vagões, 3.0 ton):	LocO_[1.0]_[2.0]
args[4]="3": Junta vagão com 3.0 ton
  (3 vagões, 6.0 ton):	LocO_[1.0]_[2.0]_[3.0]
args[5]="R": Retira vagão com 3.0 ton
  (2 vagões, 3.0 ton):	LocO_[1.0]_[2.0]
args[6]="R": Retira vagão com 2.0 ton
  (1 vagões, 1.0 ton):	LocO_[1.0]
args[7]="4.5": Junta vagão com 4.5 ton
  (2 vagões, 5.5 ton):	LocO_[1.0]_[4.5]
args[8]="0.1": Junta vagão com 0.1 ton
  (3 vagões, 5.6 ton):	LocO_[1.0]_[4.5]_[0.1]

$ java -ea MainTrain 10 100 4 2 5 7 -2 -11 -1
(Capacidade dos vagões: 10.0 ton.)
(Capacidade do comboio: 100.0 ton.)
args[2]="4": Junta vagão com 4.0 ton
  (1 vagões, 4.0 ton):	LocO_[4.0]
args[3]="2": Junta vagão com 2.0 ton
  (2 vagões, 6.0 ton):	LocO_[4.0]_[2.0]
args[4]="5": Junta vagão com 5.0 ton
  (3 vagões, 11.0 ton):	LocO_[4.0]_[2.0]_[5.0]
args[5]="7": Junta vagão com 7.0 ton
  (4 vagões, 18.0 ton):	LocO_[4.0]_[2.0]_[5.0]_[7.0]
args[6]="-2": Descarrega 2.0 ton e retira 0 vagões vazios.
  (4 vagões, 16.0 ton):	LocO_[4.0]_[2.0]_[5.0]_[5.0]
args[7]="-11": Descarrega 11.0 ton e retira 2 vagões vazios.
  (2 vagões, 5.0 ton):	LocO_[4.0]_[1.0]
args[8]="-1": Descarrega 1.0 ton e retira 1 vagões vazios.
  (1 vagões, 4.0 ton):	LocO_[4.0]

$ java -ea MainTrain 10 20 2 10 11
(Capacidade dos vagões: 10.0 ton.)
(Capacidade do comboio: 20.0 ton.)
args[2]="2": Junta vagão com 2.0 ton
  (1 vagões, 2.0 ton):	LocO_[2.0]
args[3]="10": Junta vagão com 10.0 ton
  (2 vagões, 12.0 ton):	LocO_[2.0]_[10.0]
args[4]="11": ERRO: Sobrecarga de vagão!

$ java -ea MainTrain 10 20 5 7 9
(Capacidade dos vagões: 10.0 ton.)
(Capacidade do comboio: 20.0 ton.)
args[2]="5": Junta vagão com 5.0 ton
  (1 vagões, 5.0 ton):	LocO_[5.0]
args[3]="7": Junta vagão com 7.0 ton
  (2 vagões, 12.0 ton):	LocO_[5.0]_[7.0]
args[4]="9": ERRO: Sobrecarga do comboio!

*/

