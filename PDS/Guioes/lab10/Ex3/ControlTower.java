package lab10.Ex3;

import java.util.ArrayList;
import java.util.List;

public class ControlTower implements Mediator {

    private final List<AirTransport> components;

    public ControlTower() {
        components = new ArrayList<>();
    }

    public void registerComponent(AirTransport c) {
        c.setMediator(this);
        components.add(c);
        System.out.printf("%s[%s] added.\n", c.getClass().getSimpleName(), c.getLicensePlate());
    }

    @Override
    public void notify(AirTransport sender, String event) {
        String message = String.format("%s[%s] event: %s",
                sender.getClass().getSimpleName(),
                sender.getLicensePlate(),
                event);
        for (AirTransport a : components) {
            if (!a.getLicensePlate().equals(sender.getLicensePlate())) {
                a.update(message);
            }
        }
    }
}
