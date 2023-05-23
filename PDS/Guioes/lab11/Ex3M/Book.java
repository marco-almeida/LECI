package lab11.Ex3M;

public class Book {
    private final String title;
    private final int isbn;
    private final int year;
    private final String author;
    private State state;

    public Book(String title, int isbn, int year, String author) {
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.author = author;
        this.state = new Inventory();
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public int getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format("%2d %10s %10s [%s]", isbn, title, author, state.getClass().getSimpleName());
    }

    public State getState() {
        return state;
    }

    public void register() {
        this.state.register(this);
    }
    public void request() {
        this.state.request(this);
    }
    public void returnBook() {
        this.state.returnBook(this);
    }
    public void reserve() {
        this.state.reserve(this);
    }
    public void cancelReservation() {
        this.state.cancelReservation(this);
    }
}
