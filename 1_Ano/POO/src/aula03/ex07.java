package aula03;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ex07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String resposta = "Sim";
        while (resposta.equals("Sim") || resposta.equals("S")) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 101);
            int guess = 0;
            int tentativas = 0;
            while (guess != randomNum) {
                System.out.print("Tenta adivinhar o número aleatório de 1 a 100: ");
                guess = sc.nextInt();
                tentativas++;
                if (guess > randomNum) {
                    System.out.println("Muito alto!");
                } else if (guess < randomNum) {
                    System.out.println("Muito baixo!");
                } else {
                    System.out.printf("Acertaste com %d tentativas efetuadas!%n", tentativas);
                }
            }
            System.out.print("Pretendes continuar a jogar?(Sim/S)");
            resposta = sc.next();
        }
        sc.close();
    }
}
