package aula09;

public class DateND extends Date implements Comparable<DateND> {
    private int days;

    public DateND(int days) {
        this.days = days;
    }

    @Override
    public int compareTo(DateND obj) {
        return this.days - obj.getDays();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + days;
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
        DateND other = (DateND) obj;
        if (days != other.days)
            return false;
        return true;
    }

    /**
     * decrements one single day from current date
     */
    public void decrement() {
        days--;
    }

    /**
     * increments one single day from current date
     */
    public void increment() {
        days++;
    }

    /**
     * Returns equivalent Date in DateYMD format
     * 
     * @return DateYMD object
     */
    public DateYMD ndToYmd() {
        DateYMD temp = new DateYMD(1, 1, 2000);
        if (this.days >= 0) {
            for (int i = this.days; i > 0; i--) {
                temp.increment();
            }
        } else {
            for (int i = this.days; i < 0; i++) {
                temp.decrement();
            }
        }
        return temp;
    }

    public void set(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return String.format("%s", ndToYmd().toString());
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
