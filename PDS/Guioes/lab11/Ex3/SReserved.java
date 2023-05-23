package lab11.Ex3;

public class SReserved implements State {
    private static final String CANCEL = "Cant do this operation";

    @Override
    public String toString(){
        return "Reserved";
    }

    @Override
    public void register(Book wrapper) {System.out.println(CANCEL);}

    @Override
    public void borrow(Book wrapper) {System.out.println(CANCEL);}

    @Override
    public void makeReservation(Book wrapper) {System.out.println(CANCEL);}

    @Override
    public void returns(Book wrapper) {System.out.println(CANCEL);}

    @Override
    public void cancelReservation(Book wrapper) {
        System.out.println("Reservation has been canceled");
        wrapper.setState(new SAvailable());
    }
    
}
