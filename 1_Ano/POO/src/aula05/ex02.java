package aula05;

import java.util.Scanner;

public class ex02 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int op;
        Calendar c1 = new Calendar(1, 1);
        do {
            printMenu();
            op = input.nextInt();

            switch (op) {
                case 1:
                    c1 = createNewCalendar(input);
                    break;
                case 2:
                    System.out.print("Month to print: ");
                    int m = input.nextInt();
                    c1.printMonth(m);
                    break;
                case 3:
                    System.out.println(c1.toString());
                    break;
            }
        } while (op != 0);

        input.close();
    }

    public static void printMenu() {
        System.out.println("\n\nCalendar operations:");
        System.out.println("1 - create new calendar");
        System.out.println("2 - print calendar month");
        System.out.println("3 - print calendar");
        System.out.println("0 - exit");
    }

    public static Calendar createNewCalendar(Scanner input) {
        System.out.print("First weekday of year: ");
        int day = input.nextInt();
        System.out.print("Year: ");
        int year = input.nextInt();
        return new Calendar(day, year);
    }
}
