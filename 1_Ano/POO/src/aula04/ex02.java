package aula04;

import java.util.Scanner;

public class ex02 {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Inserir frase: ");
        String str = input.nextLine();
        System.out.printf("Número de carateres numéricos: %d%n", countDigits(str));
        System.out.printf("Número de espaços: %d%n", countSpaces(str));
        System.out.printf("Contém apenas minúsculas: %b%n", onlyContainsLowerCase(str));
        System.out.printf("Trimmed: %s%n", trimIt(str));
        System.out.printf("É palíndromo?: %b%n", isPalindrom(str));
    }

    public static int countDigits(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }

    public static int countSpaces(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                count++;
            }
        }
        return count;
    }

    public static boolean onlyContainsLowerCase(String str) {
        return str.toLowerCase().equals(str);
    }

    public static String trimIt(String str) {
        return str.trim().replaceAll(" +", " ");
    }

    public static boolean isPalindrom(String str) {
        StringBuilder input1 = new StringBuilder();
        input1.append(str);
        input1.reverse();
        return input1.toString().equals(str);
    }
}
