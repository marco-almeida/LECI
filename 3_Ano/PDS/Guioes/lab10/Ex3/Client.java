package lab10.Ex3;

public class Client {
    public static void main(String[] args) {
        Mediator mediator = new ControlTower();
        AirTransport a = new Helicopter("HE-34-AE");
        AirTransport b = new CommercialPlane("PQ-59-XU");
        AirTransport c = new CargoPlane("LA-10-QL");
        mediator.registerComponent(a);
        a.land();
        mediator.registerComponent(b);
        b.takeoff();
        mediator.registerComponent(c);
        c.passBy();
        AirTransport d = new Helicopter("HE-01-PL");
        d.setMediator(mediator);
        d.takeoff();
    }
}
