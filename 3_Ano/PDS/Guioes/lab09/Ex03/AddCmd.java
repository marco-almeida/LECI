package lab09.Ex03;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class AddCmd implements Command {

    private final Collection collection;
    private final Deque<Object> elements = new ArrayDeque<>();

    public AddCmd(Collection collection) {
        this.collection = collection;
    }

    @Override
    public boolean execute(Object element) {
        if (collection.add(element)) {
            elements.push(element);
            return true;
        }
        return false;
    }

    @Override
    public boolean undo() {
        if (elements.isEmpty()) {
            System.out.println("Error: No undo operation possible.");
            return false;
        }
        return collection.remove(elements.pop());
    }
}
