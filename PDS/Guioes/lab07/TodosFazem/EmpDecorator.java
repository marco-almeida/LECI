package lab07.TodosFazem;

import java.util.Date;

public abstract class EmpDecorator implements EmployeeInterface {
    protected EmployeeInterface e;

    EmpDecorator(EmployeeInterface e) {
        this.e = e;
    }

    public void start(Date d) {
        e.start(d);
    }

    public void terminate(Date d) {
        e.terminate(d);
    }

    public void work() {
        e.work();
    }
}
