package aula04;

import java.util.Scanner;

public class ex01 {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        String frase;
        do {
            System.out.print("Inserir frase com pelo menos 3 carateres: ");
            frase = input.nextLine();
        } while (frase.length() < 3);
        System.out.printf("Nova frase convertida para minúsculas: %s%n", frase.toLowerCase());
        System.out.printf("Último carater da frase: %c%n", frase.charAt(frase.length() - 1));
        System.out.printf("3 primeiros carateres da frase: %s%n", frase.substring(0, 3));
        System.out.printf("Nova frase convertida para maiúsculas: %s%n", frase.toUpperCase());
        System.out.printf("Último carater da frase: %c%n", frase.charAt(frase.length() - 1));
        System.out.printf("CodePoint do primeiro carater: %d%n", frase.codePointAt(0));
        System.out.printf("Verificar se frase contem a letra 'Z': %b%n", frase.contains("Z") || frase.contains("z"));
    }
}
