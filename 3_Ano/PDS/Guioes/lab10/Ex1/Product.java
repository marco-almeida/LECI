package lab10.Ex1;

public class Product extends Observable {
    private static int lastAssignedCode = 0;
    private final int code;
    private final String description;
    private final double basePrice;
    private double price;
    private Pstate state;

    public Product(String description, double basePrice) {
        lastAssignedCode++;
        this.code = lastAssignedCode;
        this.description = description;
        this.basePrice = basePrice;
        this.price = basePrice;
        this.state = Pstate.STOCK;
    }

    public Pstate getState() {
        return state;
    }

    public void setState(Pstate newState) {
        state = newState;
    }

    public double getPrice() {
        return price;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void notifyObservers() {
        for (Observer obs : listOfObservers) {
            obs.update(this.code);
        }
    }

    public void bid(Client client, double price) {
        System.out.println("Client " + client + " has bid " + price + " on product " + description);
        if (this.state == Pstate.STOCK) {
            System.out.println("This item is not up for bidding right now.");
        } else if (this.state == Pstate.VENDIDO) {
            System.out.println("This item has already been sold.");
        } else if (price <= this.price) {
            System.out.println("Bidding price is lower than current items price.");
        } else {
            subscribe(client);
            this.price = price;
            notifyObservers();
        }
        System.out.println("-----");
    }

    public void closeBidding() {
        System.out.println("Bidding is closed.");

        if (price == basePrice) {
            state = Pstate.STOCK;
        } else {
            state = Pstate.VENDIDO;
            notifyObservers();
        }
    }

}