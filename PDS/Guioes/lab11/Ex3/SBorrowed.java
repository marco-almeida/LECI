package lab11.Ex3;

public class SBorrowed implements State {
    private static final String CANCEL = "Cant do this operation";

    @Override
    public String toString(){
        return "Borrowed";
    }

    @Override
    public void register(Book wrapper) {System.out.println(CANCEL);}

    @Override
    public void borrow(Book wrapper) {System.out.println(CANCEL);}

    @Override
    public void makeReservation(Book wrapper) {System.out.println(CANCEL);}

    @Override
    public void returns(Book wrapper) {
        System.out.println("Book has been returned");
        wrapper.setState(new SAvailable());
    }

    @Override
    public void cancelReservation(Book wrapper) {System.out.println(CANCEL);}
    
}
