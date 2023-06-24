package lab08.ex2;

import java.util.ArrayList;
import java.util.List;

public class SocialSecurity {
    private List<Person> people;
    private static SocialSecurity instance;

    private SocialSecurity() {
        this.people = new ArrayList<>();
    }

    public static SocialSecurity getInstance() {
        if (instance == null) {
            instance = new SocialSecurity();
        }
        return instance;
    }

    public boolean regist(Person p) {
        System.out.printf("Person %s successfully registered into social security.\n", p.getName());
        return people.add(p);
    }
}
