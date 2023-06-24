package lab07.TodosFazem;

import java.util.Date;

public class TeamLeader extends EmpDecorator {

    TeamLeader(EmployeeInterface e) {
        super(e);
    }

    @Override
    public void start(Date d) {
        super.start(d);
        System.out.print("as teamLeader ");
    }

    @Override
    public void terminate(Date d) {
        super.terminate(d);
        System.out.print("as teamLeader ");
    }

    @Override
    public void work() {
        super.work();
        System.out.print("as teamLeader ");
    }

    public void plan() {
        System.out.print("plans ");
    }
}
