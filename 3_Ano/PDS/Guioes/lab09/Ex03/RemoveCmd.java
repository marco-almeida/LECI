package lab09.Ex03;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class RemoveCmd implements Command {

    private final Collection collection;
    private final Deque<Object> elements = new ArrayDeque<>();

    public RemoveCmd(Collection collection) {
        this.collection = collection;
    }

    @Override
    public boolean execute(Object element) {
        if (collection.remove(element)) {
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
