package aula07;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DateYMD extends Date {

    private int day, month, year;

    public DateYMD(int day, int month, int year) {
        if (valid(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        }
    }

    public DateYMD() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.day = Integer.parseInt(dtf.format(now).split("/")[2]);
        this.month = Integer.parseInt(dtf.format(now).split("/")[1]);
        this.year = Integer.parseInt(dtf.format(now).split("/")[0]);
    }

    /**
     * decrements one single day from current date
     */
    public void decrement() {
        day--;
        if (day == 0) {
            month--;
            if (!validMonth(month)) {
                month = 12;
                year--;
            }
            day = monthDays(month, year);
        }
    }

    /**
     * increments one single day from current date
     */
    public void increment() {
        day++;
        if (!valid(day, month, year)) {
            month++;
            day = 1;
            if (!valid(day, month, year)) {
                year++;
                month = 1;
            }
        }
    }

    public void set(int day, int month, int year) {
        if (valid(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        }
    }

    /**
     * Returns equivalent Date in DateND format
     * 
     * @return DateND object
     */
    public DateND ymdToNd() {
        int days = 0;
        DateYMD temp = new DateYMD(day, month, year);
        if(!valid(day, month, year)){
            return new DateND(0);
        }
        if (year >= 2000 && month >= 1 && day >= 1) {
            while (temp.getYear() != 2000 || temp.getDay() != 1 || temp.getMonth() != 1) {
                temp.decrement();
                days++;
            }
        } else {
            while (temp.getYear() != 2000 || temp.getDay() != 1 || temp.getMonth() != 1) {
                temp.increment();
                days--;
            }
        }
        return new DateND(days);
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
