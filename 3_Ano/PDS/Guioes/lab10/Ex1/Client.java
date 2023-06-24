package lab10.Ex1;

public class Client extends Observer {
    String name;
    Leilao event;

    public Client(String name, Leilao event) {
        this.name = name;
        this.event = event;
    }

    @Override
    public String toString() {//overriding the toString() method
        return name;
    }

    @Override
    public void update(int code) {
        System.out.print(name + " update: ");
        for (Product prd : event.listOfProducts) {
            if (prd.getCode() == code && prd.getState() == Pstate.VENDIDO) {
                System.out.println(prd.getDescription() + " has been sold");
            }
            if (prd.getCode() == code && prd.getState() == Pstate.LEILAO) {
                System.out.println(prd.getDescription() + " has been bid for " + prd.getPrice());
            }
        }
    }

}
