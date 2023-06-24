package lab07.TodosFazem;

import java.util.Date;

public class TeamMember extends EmpDecorator {

    TeamMember(EmployeeInterface e) {
        super(e);
    }

    @Override
    public void start(Date d) {
        super.start(d);
        System.out.print("as teamMember ");
    }

    @Override
    public void terminate(Date d) {
        super.terminate(d);
        System.out.print("as teamMember ");
    }

    @Override
    public void work() {
        super.work();
        System.out.print("as teamMember ");
    }

    public void colaborate() {
        System.out.print("collaborates ");
    }
}
