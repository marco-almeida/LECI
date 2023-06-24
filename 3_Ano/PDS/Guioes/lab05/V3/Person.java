package lab05.V3;

public class Person {
    private final int age;
    private final String name;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }
}
