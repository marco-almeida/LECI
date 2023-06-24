package lab11.Ex3M;

public class Inventory implements State {
    @Override
    public void register(Book book) {
        book.setState(new Available());
    }

    @Override
    public void reserve(Book book) {
        System.out.println("Operation not available;");
    }

    @Override
    public void request(Book book) {
        System.out.println("Operation not available;");
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
