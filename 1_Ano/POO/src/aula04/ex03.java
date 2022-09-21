package aula04;

import java.util.Scanner;

public class ex03 {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Introduza uma frase:");
        String str = input.nextLine();
        for (String word : str.split(" ")) {
            if (word.length() >= 3){
                System.out.printf("%c", Character.toUpperCase(word.charAt(0)));
            }
        }
    }
}
