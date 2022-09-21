package aula02;

import java.util.Scanner;

public class ex05 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.print("Velocidade média com que é percorrida a 1ª metade do caminho: ");
        double v1 = ler.nextDouble();
        System.out.print("Distância percorrida nessa parte do caminho: ");
        double d1 = ler.nextDouble();
        System.out.print("Velocidade média com que é percorrida a 2ª metade do caminho: ");
        double v2 = ler.nextDouble();
        System.out.print("Distância percorrida nessa parte do caminho: ");
        double d2 = ler.nextDouble();
        ler.close();
        System.out.printf("Velocidade média final: %4.2f", (d1 + d2) / ((d1 / v1) + (d2 / v2)));
    }
}
