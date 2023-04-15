package lab07.TextProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TermFilter extends TextDecorator {
    private List<String> wordsInMemory;

    protected TermFilter(TextInterface t) {
        super(t);
        wordsInMemory = new ArrayList<>(Arrays.asList(super.next().split(" ")));
    }

    @Override
    public boolean hasNext() {
        return !wordsInMemory.isEmpty() || super.hasNext();
    }

    @Override
    public String next() {
        // refill buffer with next paragraph
        if (wordsInMemory.isEmpty()) {
            wordsInMemory = new ArrayList<>(Arrays.asList(super.next().split(" ")));
        }
        String res = wordsInMemory.get(0);
        wordsInMemory.remove(0);
        return res;
    }
}
