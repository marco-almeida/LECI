package guiao01;

import java.util.Scanner;
import java.util.Stack;

public class Ex03 {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // usar nextdouble como o stor disse
        Stack<Double> stack = new Stack<>();
        String[] operations = getUserInput();
        double n1;
        double n2;
        for (String op : operations) {
            switch (op) {
                case "+":
                    n1 = stack.pop();
                    n2 = stack.pop();
                    stack.add(n1 + n2);
                    break;
                case "-":
                    n1 = stack.pop();
                    n2 = stack.pop();
                    stack.add(n1 - n2);
                    break;
                case "*":
                    n1 = stack.pop();
                    n2 = stack.pop();
                    stack.add(n1 * n2);
                    break;
                case "/":
                    n1 = stack.pop();
                    n2 = stack.pop();
                    stack.add(n1 / n2);
                    break;
                default:
                    stack.push(Double.parseDouble(op));
            }
            System.out.println("Stack: " + stack.toString());
        }
        System.out.println(stack.peek());
    }

    public static String[] getUserInput() {
        String userInput = input.nextLine();
        return userInput.split(" ");
    }
}
