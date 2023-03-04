package guiao01;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Ex03 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Deque<Double> stack = new ArrayDeque<>();

        while (input.hasNextLine()) {
            String userInput = input.nextLine();
            String[] operations = userInput.split(" ");

            for (String op : operations) {
                try {
                    distributeFunction(stack, op);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Make sure to insert either a number or an operation");
                }
                System.out.println("Stack: " + stack.toString());
            }
        }
        input.close();
    }

    public static void distributeFunction(Deque<Double> stack, String op) {
        if ("+-*/".contains(op) && stack.size() < 2) {
            System.out.println("Error: Not enough numbers in stack");
            return;
        }
        double n1;
        double n2;
        switch (op) {
            case "+":
                n1 = stack.pop();
                n2 = stack.pop();
                stack.push(n1 + n2);
                break;
            case "-":
                n1 = stack.pop();
                n2 = stack.pop();
                stack.push(n1 - n2);
                break;
            case "*":
                n1 = stack.pop();
                n2 = stack.pop();
                stack.push(n1 * n2);
                break;
            case "/":
                n1 = stack.pop();
                n2 = stack.pop();
                stack.push(n1 / n2);
                break;
            default:
                stack.push(Double.parseDouble(op));
        }
    }
}
