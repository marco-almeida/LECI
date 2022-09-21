package aula05;

import java.util.Scanner;

public class ex01 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int op;
        Date d1 = new Date(0, 0, 0);
        do {
            printMenu();
            op = input.nextInt();

            switch (op) {
                case 1:
                    setNewDate(d1, input);
                    break;
                case 2:
                    System.out.println(d1.toString());
                    break;
                case 3:
                    d1.increment();
                    break;
                case 4:
                    d1.decrement();
                    break;
            }
        } while (op != 0);

        input.close();
    }

    public static void printMenu() {
        System.out.println("\n\nDate operations:");
        System.out.println("1 - create new date");
        System.out.println("2 - show current date");
        System.out.println("3 - increment date");
        System.out.println("4 - decrement date");
        System.out.println("0 - exit");
    }

    public static void setNewDate(Date date, Scanner input) {
        System.out.print("Day: ");
        int day = input.nextInt();
        System.out.print("Month: ");
        int month = input.nextInt();
        System.out.print("Year: ");
        int year = input.nextInt();
        date.set(day, month, year);
    }
}
