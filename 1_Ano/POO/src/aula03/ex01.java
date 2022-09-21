package aula03;

import java.util.Scanner;

public class ex01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nota prática: ");
        double np = sc.nextDouble();
        while (np < 0 || np > 20) {
            System.out.println("Nota inválida, introduzir número real de 0 a 20: ");
            np = sc.nextDouble();
        }
        System.out.print("Nota teórica: ");
        double nt = sc.nextDouble();
        while (nt < 0 || nt > 20) {
            System.out.println("Nota inválida, introduzir número real de 0 a 20: ");
            nt = sc.nextDouble();
        }
        sc.close();
        double nf = 66;
        if (nt > 7 && np > 7) {
            nf = 0.4 * nt + 0.6 * np;
        }
        System.out.println("Nota final: " + Math.round(nf));
    }
}
