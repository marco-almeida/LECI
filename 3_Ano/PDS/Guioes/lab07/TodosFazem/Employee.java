package lab07.TodosFazem;

import java.util.Date;

public class Employee implements EmployeeInterface {

    private String name;

    public Employee(String name) {
        this.name = name;
    }

    @Override
    public void start(Date d) {
        System.out.printf("\n %s starts at %s ", name, d);
    }

    @Override
    public void terminate(Date d) {
        System.out.printf("%s terminates at %s ", name, d);
    }

    @Override
    public void work() {
        System.out.printf("%s works ", name);
    }
}
