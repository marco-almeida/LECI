package aula07;

public class DateND extends Date {
    private int days;

    public DateND(int days) {
        this.days = days;
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
