package lab08.ex2;

import java.util.ArrayList;
import java.util.List;

public class Insurance {
    private List<Person> people;
    private static Insurance instance;

    private Insurance() {
        this.people = new ArrayList<>();
    }

    public static Insurance getInstance() {
        if (instance == null) {
            instance = new Insurance();
        }
        return instance;
    }

    public boolean regist(Person p) {
        System.out.printf("Person %s successfully registered into insurance.\n", p.getName());
        return people.add(p);
    }
}
