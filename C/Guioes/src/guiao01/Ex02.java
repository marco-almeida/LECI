package guiao01;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import guiao01.Ex01;

public class Ex02 {
    public static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        String[] numbersAndOperator = getNumbersAndOperator();
        Map<String,Double> registry = new HashMap<>();
        // seca

    }

    public static String[] getNumbersAndOperator() {
        String userInput = input.nextLine();
        return userInput.split(" ");
    }
}
