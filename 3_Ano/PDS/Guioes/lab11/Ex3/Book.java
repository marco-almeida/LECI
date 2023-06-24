package lab11.Ex3;

public class Book{
    String title;
    String ISBN;
    int year;
    String author;
    State state;

    public Book(String title, String ISBN, int year, String author){
        this.title = title;
        this.ISBN = ISBN;
        this.year = year;
        this.author = author;
        this.state = new SInventory();
    }

    @Override
    public String toString(){
        return title + " - " + author + " [" + state + "]";
    }

    public void setState(State state){
        this.state = state;
    }

    public void register() {state.register(this);}
    public void borrow() {state.borrow(this);}
    public void makeReservation() {state.makeReservation(this);}
    public void returns() {state.returns(this);}
    public void cancelReservation() {state.cancelReservation(this);}

}