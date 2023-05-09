package lab09.Ex03;

import java.util.ArrayList;
import java.util.Collection;

public class Ex03 {
    public static void main(String[] args) {
        Collection<String> list = new ArrayList<>();
        Command add = new AddCmd(list);
        Command remove = new RemoveCmd(list);
        Controller controller = new Controller();

        controller.setCommand(add);
        controller.execute("a");
        controller.execute("b");
        controller.execute("c");

        System.out.println(list);

        controller.undo();

        System.out.println(list);

        controller.setCommand(remove);
        controller.execute("a");

        System.out.println(list);

        controller.undo();

        System.out.println(list);

        controller.undo();
    }
}
