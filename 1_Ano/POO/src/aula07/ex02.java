package aula07;

import Helper.Utils;

public class ex02 {
    public static void main(String[] args) {
        int op;
        Date d1 = new DateYMD();
        do {
            printMenu();
            op = Utils.nextInt("");
            switch (op) {
                case 1:
                    d1 = setNewDate();
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

    }

    public static void printMenu() {
        System.out.println("\n\nDate operations:");
        System.out.println("1 - create new date");
        System.out.println("2 - show current date");
        System.out.println("3 - increment date");
        System.out.println("4 - decrement date");
        System.out.println("0 - exit");
    }

    public static DateYMD setNewDate() {
        int day = Utils.nextInt("Day: ");
        int month = Utils.nextInt("Month: ");
        int year = Utils.nextInt("Year: ");
        return new DateYMD(day, month, year);
    }
}
