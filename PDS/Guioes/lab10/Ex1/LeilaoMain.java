package lab10.Ex1;

public class LeilaoMain {

    public static void main(String[] args) {
        Leilao event = new Leilao();

        Product p1 = new Product("Quadro realista", 100);
        Product p2 = new Product("Vaso", 50);
        Product p3 = new Product("estatua", 500);
        Product p4 = new Product("busto", 145);
        Product p5 = new Product("Quadro abstracto", 1000);

        event.add(p1);
        event.add(p2);
        event.add(p3);
        event.add(p4);
        event.add(p5);

        Manager manager = new Manager("M0", event);
        p1.subscribe(manager);
        p2.subscribe(manager);
        p3.subscribe(manager);
        p4.subscribe(manager);
        p5.subscribe(manager);

        Client c1 = new Client("C1", event);
        Client c2 = new Client("C2", event);
        Client c3 = new Client("C3", event);

        System.out.println("----------------------------------------------------");
        p1.setState(Pstate.LEILAO);
        p1.bid(c3, 0);
        p1.bid(c2, 2000);
        p2.bid(c3, 1900);
        p1.bid(c1, 2010);
        p1.bid(c2, 2040);
        p1.closeBidding();

        System.out.println("----------------------------------------------------");
        p3.setState(Pstate.LEILAO);
        p3.bid(c2, 600);
        p3.bid(c1, 650);
        p3.bid(c1, 700);
        p3.closeBidding();

        System.out.println("----------------------------------------------------");
        p4.setState(Pstate.LEILAO);
        p4.closeBidding();


    }

}
