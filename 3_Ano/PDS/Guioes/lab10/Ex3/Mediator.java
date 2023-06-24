package lab10.Ex3;

public interface Mediator {
    void notify(AirTransport sender, String event);
    void registerComponent(AirTransport c);
}
