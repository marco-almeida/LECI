
package lab07.TodosFazem;

import java.util.Date;

public class Manager extends EmpDecorator {

    Manager(EmployeeInterface e) {
        super(e);
    }

    @Override
    public void start(Date d) {
        super.start(d);
        System.out.print("as manager ");
    }

    @Override
    public void terminate(Date d) {
        super.terminate(d);
        System.out.print("as manager ");
    }

    @Override
    public void work() {
        super.work();
        System.out.print("as manager ");
    }

    public void manage() {
        System.out.print("manages ");
    }
}
