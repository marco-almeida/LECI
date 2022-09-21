package aula04;

import java.util.Scanner;

public class ex04 {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int[] data = readValues();
        printResults(data[0], data[1], data[2]);
    }

    public static int[] readValues() {
        int ano;
        int mes;
        int dia;
        do {
            System.out.print("Inserir ano: ");
            ano = input.nextInt();
        } while (ano < 0);

        do {
            System.out.print("Inserir mês: ");
            mes = input.nextInt();
        } while (mes < 1 || mes > 12);

        do {
            System.out.print("Inserir primeiro dia da semana do mês: ");
            dia = input.nextInt();
        } while (dia < 1 || dia > 7);

        return new int[] { ano, mes, dia };
    }

    public static int daysInMonth(int ano, int mes) {
        int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (leapYear(ano)) {
            days[1] = 29;
        }
        return days[mes - 1];
    }

    private static boolean leapYear(int ano) {
        return ((ano % 400 == 0) || ((ano % 4 == 0) && (ano % 100 != 0)));
    }

    public static void printResults(int ano, int mes, int dia) {
        String[] meses = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        int[] spaces = { 3, 6, 9, 12, 15, 18, 0 };
        String titulo = meses[mes - 1] + " " + ano;
        System.out.printf("%n%s%s", " ".repeat((20 - titulo.length()) / 2), titulo); // center string
        System.out.print("%nSu Mo Tu We Th Fr Sa%n");
        System.out.print(" ".repeat(spaces[dia - 1])); // spacing

        for (int i = 1; i <= daysInMonth(ano, mes); i++) { // print days, print %n when saturday
            System.out.printf("%2d ", i);
            dia++;
            if (dia == 7 || dia == 14) {
                System.out.println("");
                dia = 0;
            }
        }
    }
}
