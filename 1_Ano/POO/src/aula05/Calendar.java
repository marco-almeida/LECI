package aula05;

public class Calendar {
    private int firstWeekdayOfYear, year;
    private final static String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December" };

    public Calendar(int firstWeekdayOfYear, int year) {
        if (firstWeekdayOfYear > 0 && firstWeekdayOfYear < 8) {
            this.firstWeekdayOfYear = firstWeekdayOfYear;
            this.year = year;
        } else {
            System.out.println("First weekday of year invalid.");
        }
    }

    public void printMonth(int month) {
        int fw = firstWeekdayOfMonth(month);
        String titulo = months[month - 1] + " " + year;
        System.out.printf("\n\n%s%s", " ".repeat((20 - titulo.length()) / 2), titulo); // center string
        System.out.printf("\nSu Mo Tu We Th Fr Sa\n");
        System.out.printf(String.format(" ".repeat((fw - 1) * 3))); // spacing
        for (int k = 1; k <= Date.monthDays(month, year); k++) { // print days, print \n when saturday
            System.out.printf("%2d ", k);
            if (fw == 7) {
                System.out.printf("\n");
                fw = 0;
            }
            fw++;
        }
    }

    public int firstWeekdayOfMonth(int month) {
        int diaspassados = firstWeekdayOfYear;
        for (int i = 1; i < month; i++) {
            diaspassados += Date.monthDays(i, year);
        }
        while (diaspassados > 7) {
            diaspassados -= 7;
        }
        return diaspassados;
    }

    @Override
    public String toString() {
        int firstWeekday = firstWeekdayOfYear;
        StringBuilder sb = new StringBuilder("");

        for (int i = 1; i <= 12; i++) {
            String titulo = months[i - 1] + " " + year;
            sb.append(String.format("\n\n%s%s", " ".repeat((20 - titulo.length()) / 2), titulo)); // center string
            sb.append("\nSu Mo Tu We Th Fr Sa\n");
            sb.append(String.format(" ".repeat((firstWeekday - 1) * 3))); // spacing

            for (int k = 1; k <= Date.monthDays(i, year); k++) { // print days, print \n when saturday
                sb.append(String.format("%2d ", k));
                if (firstWeekday == 7) {
                    sb.append("\n");
                    firstWeekday = 0;
                }
                firstWeekday++;
            }
        }
        return sb.toString();
    }

    public int getFirstWeekdayOfYear() {
        return firstWeekdayOfYear;
    }

    public void setFirstWeekdayOfYear(int firstWeekdayOfYear) {
        this.firstWeekdayOfYear = firstWeekdayOfYear;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
