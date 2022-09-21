package aula07;

public abstract class Date {
    public abstract void decrement();

    public abstract void increment();

    public abstract String toString();

    public static boolean valid(int day, int month, int year) {
        if (year < 1) {
            return false;
        }
        if (!validMonth(month)) {
            return false;
        }
        if (day < 1 || day > monthDays(month, year)) {
            return false;
        }
        return true;
    }

    public static boolean validMonth(int month) {
        return month > 0 && month < 13;
    }

    public static int monthDays(int month, int year) {
        int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (leapYear(year)) {
            days[1] = 29;
        }
        return days[month - 1];
    }

    public static boolean leapYear(int year) {
        return ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)));
    }

}
