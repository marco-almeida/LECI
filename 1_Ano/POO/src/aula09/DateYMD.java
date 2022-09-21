package aula09;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class DateYMD extends Date implements Comparable<DateYMD> {

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

    @Override
    public int compareTo(DateYMD o) {
        if (equals(o)) {
            return 0;
        }
        if (this.year > o.getYear()) {
            return 1;
        } else if (this.year < o.getYear()) {
            return -1;
        }
        if (this.month > o.getMonth()) {
            return 1;
        } else if (this.month < o.getMonth()) {
            return -1;
        }
        if (this.day > o.getDay()) {
            return 1;
        } else {
            return -1;
        }
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
        if (!valid(day, month, year)) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + day;
        result = prime * result + month;
        result = prime * result + year;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DateYMD other = (DateYMD) obj;
        if (day != other.day)
            return false;
        if (month != other.month)
            return false;
        if (year != other.year)
            return false;
        return true;
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
