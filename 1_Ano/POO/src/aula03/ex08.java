package aula03;

import java.util.concurrent.ThreadLocalRandom;

public class ex08 {
    public static void main(String[] args) {

        double[][] notas = new double[16][2];
        System.out.printf("%7s%7s%7s%n", "NotaT", "NotaP", "Pauta");
        for (int i = 0; i < notas.length; i++) {
            for (int j = 0; j < notas[i].length; j++) {
                notas[i][j] = ThreadLocalRandom.current().nextDouble(0, 21);
                System.out.printf("%7.1f", notas[i][j]);
            }
            if (notas[i][0] > 7 && notas[i][1] > 7) {
                System.out.printf("%7d%n", (int) Math.round(notas[i][0] * 0.4 + 0.6 * notas[i][1]));
            } else {
                System.out.printf("%7d%n", 66);
            }
        }
    }
}
