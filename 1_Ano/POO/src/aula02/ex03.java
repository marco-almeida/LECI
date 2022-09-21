package aula02;

import java.util.Scanner;

public class ex03 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.print("Massa (KG): ");
        double m = ler.nextDouble();
        System.out.print("Temperatura inicial (C): ");
        double ti = ler.nextDouble();
        System.out.print("Temperatura final (C): ");
        double tf = ler.nextDouble();
        ler.close();
        System.out.printf("Energia necessária: %4.2f", m * (tf - ti) * 4184);
    }
}
