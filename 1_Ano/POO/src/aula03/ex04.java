package aula03;

import java.util.Scanner;

public class ex04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserir número real: ");
        double soma;
        double max;
        double min;
        double primeiro = soma = max = min = sc.nextDouble();
        double input = primeiro - 1;
        int num = 1;
        while (input != primeiro) {
            System.out.print("Inserir número real: ");
            input = sc.nextDouble();
            if (input > max) {
                max = input;
            }
            if (input < min) {
                min = input;
            }
            num++;
            soma += input;
        }
        sc.close();
        System.out.printf("Valor máximo: %4.2f%nValor mínimo: %4.2f%n Média: %4.2f%n Total de números: %d", max, min,
                soma / num, num);
    }
}
