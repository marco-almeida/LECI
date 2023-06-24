package lab09.Ex03;

public class Controller {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public boolean execute(Object element) {
        if (command == null) {
            System.out.println("Error: Set a comand first.");
            return false;
        }
        return command.execute(element);
    }

    public boolean undo() {
        if (command == null) {
            System.out.println("Error: Set a comand first.");
            return false;
        }
        return command.undo();
    }

}
