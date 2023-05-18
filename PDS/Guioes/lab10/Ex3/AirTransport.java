package lab10.Ex3;

public abstract class AirTransport {
    protected Mediator dialog = null;
    private final String licensePlate;

    protected AirTransport(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setMediator(Mediator m) {
        dialog = m;
    }

    public void land() {
        dialog.notify(this, "land");
    }

    public void takeoff() {
        dialog.notify(this, "takeoff");
    }

    public void passBy() {
        dialog.notify(this, "passBy");
    }

    public void update(String message) {
        System.out.printf("%s %s: Message Received from Tower: %s\n",
                this.getClass().getSimpleName(),
                this.getLicensePlate(),
                message);
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}
