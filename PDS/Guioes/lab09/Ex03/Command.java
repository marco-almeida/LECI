package lab09.Ex03;

public interface Command {
    boolean execute(Object element);

    boolean undo();
}
