package aula02;

import java.util.Scanner;

public class ex07 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.print("Ponto 1.\nCoordenadas x: ");
        double x1 = ler.nextDouble();
        System.out.print("Coordenadas y: ");
        double y1 = ler.nextDouble();
        System.out.print("Ponto 2.\nCoordenadas x: ");
        double x2 = ler.nextDouble();
        System.out.print("Coordenadas y: ");
        double y2 = ler.nextDouble();
        ler.close();
        System.out.printf("Distância entre p1 e p2: %4.2f", Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }
}
