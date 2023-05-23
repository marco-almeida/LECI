package lab11.Ex3M;

public class Available implements State {
    @Override
    public void register(Book book) {
        System.out.println("Operation not available;");
    }

    @Override
    public void reserve(Book book) {
        book.setState(new Reserved());
    }

    @Override
    public void request(Book book) {
        book.setState(new Borrowed());
    }

    @Override
    public void cancelReservation(Book book) {
        System.out.println("Operation not available;");
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("Operation not available;");
    }
}
