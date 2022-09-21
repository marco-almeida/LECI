package aula02;

import java.util.Scanner;

public class ex02 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.print("Número a converter (C): ");
        double a = ler.nextDouble();
        ler.close();
        System.out.printf("F: %4.2f", 1.8 * a + 32);
    }
}
