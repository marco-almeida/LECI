package lab11.Ex3M;

public interface State {
    void register(Book book);
    void reserve(Book book);
    void request(Book book);
    void cancelReservation(Book book);
    void returnBook(Book book);
}
