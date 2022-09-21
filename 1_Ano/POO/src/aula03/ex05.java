package aula03;

import java.util.Scanner;

public class ex05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int montante;
        double juros;
        double valor;
        do {
            System.out.print("Inserir montante inicial maior que 0 e multiplo de 1000: ");
            montante = sc.nextInt();
        } while (montante < 0 || montante % 1000 != 0);

        do {
            System.out.print("Inserir taxa de juro mensal entre 0% e 5%: ");
            juros = sc.nextDouble();
        } while (juros < 0 || juros > 5);

        sc.close();
        valor = montante;
        for (int i = 1; i <= 12; i++) {
            valor = valor * (juros / 100 + 1);
            System.out.printf("Valor ao fim do mês %d: %.2f$%n", i, valor);
        }
    }
}
