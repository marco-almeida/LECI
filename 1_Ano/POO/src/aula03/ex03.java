package aula03;

import java.util.Scanner;

public class ex03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Verificar se número natural é primo: ");
        int n = sc.nextInt();
        while (n <= 0) {
            System.out.println("Número inválido, inserir número positivo: ");
            n = sc.nextInt();
        }
        sc.close();

        for (int i = 2; i <= n / 2; ++i) {
            if (n % i == 0) {
                System.out.println("Não é primo!");
                System.exit(0);
            }
        }
        if (n == 1){
            System.out.println("Não é primo");
        } else {
            System.out.println("É primo!");
        }

    }
}
