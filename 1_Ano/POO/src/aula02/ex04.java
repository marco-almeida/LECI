package aula02;

import java.util.Scanner;

public class ex04 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.print("Montante a investir: ");
        double m = ler.nextDouble();
        System.out.print("Taxa de juro mensal: ");
        double t = ler.nextDouble();
        ler.close();
        System.out.printf("Valor total após 3 meses: %4.2f", Math.pow(1 + t / 100, 3) * m);
    }
}
