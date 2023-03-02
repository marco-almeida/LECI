package guiao01;

import java.util.*;

public class Ex01 {
    public static void main(String[] args) {
        System.out.print("Operation (number op number): ");
        String[] numbersAndOps = getNumbersAndOperator();
        String operator = numbersAndOps[1];
        double[] numbers = parseNumbers(numbersAndOps);
        double results = calculateResults(operator, numbers);
        System.out.println(results);
    }

    public static double calculateResults(String operator, double[] numbers) {
        switch (operator) {
            case "+":
                return numbers[0] + numbers[1];
            case "-":
                return numbers[0] - numbers[1];
            case "*":
                return numbers[0] * numbers[1];
            case "/":
                return numbers[0] / numbers[1];
            default:
                System.out.printf("Invalid operator \"%s\"\n", operator);
                System.exit(1);
                return 0;
        }
    }

    public static String[] getNumbersAndOperator() {
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        input.close();
        String[] output = userInput.split(" ");
        if (output.length != 3) {
            System.out.println("ERROR: read number failure");
            System.exit(1);
        }
        return output;
    }

    public static double[] parseNumbers(String[] numbersAndOps) {
        double firstNumber = 0;
        double secondNumber = 0;
        try {
            firstNumber = Double.parseDouble(numbersAndOps[0]);
            secondNumber = Double.parseDouble(numbersAndOps[2]);
        } catch (Exception e) {
            System.out.println("ERROR: read number failure");
            System.exit(1);
        }
        return new double[] { firstNumber, secondNumber };
    }
}