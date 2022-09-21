package aula02;

import java.util.Scanner;

public class ex01 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.print("Número a converter (km): ");
        double a = ler.nextDouble();
        ler.close();
        System.out.printf("Milhas: %4.2f", a / 1.609);
    }
}
