package lab08.ex2;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    private List<Person> people;
    private static Parking instance;

    private Parking() {
        this.people = new ArrayList<>();
    }

    public static Parking getInstance() {
        if (instance == null) {
            instance = new Parking();
        }
        return instance;
    }

    public boolean allow(Person p) {
        System.out.printf("Person %s allowed into parking space.\n", p.getName());
        return people.add(p);
    }
}
