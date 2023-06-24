package lab11.Ex3;

public interface State {
    public void register(Book wrapper);
    public void borrow(Book wrapper);
    public void makeReservation(Book wrapper);
    public void returns(Book wrapper);
    public void cancelReservation(Book wrapper);
}
