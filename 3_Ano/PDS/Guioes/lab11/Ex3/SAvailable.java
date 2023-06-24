package lab11.Ex3;

public class SAvailable implements State {
    private static final String CANCEL = "Cant do this operation";

    @Override
    public String toString(){
        return "Availale";
    }

    @Override
    public void register(Book wrapper) {System.out.println(CANCEL);}

    @Override
    public void borrow(Book wrapper) {
        System.out.println("Book has been borrowed");
        wrapper.setState(new SBorrowed());
    }

    @Override
    public void makeReservation(Book wrapper) {
        System.out.println("Book has been reserved");
        wrapper.setState(new SReserved());
    }

    @Override
    public void returns(Book wrapper) {System.out.println(CANCEL);}

    @Override
    public void cancelReservation(Book wrapper) {System.out.println(CANCEL);}
    
}
