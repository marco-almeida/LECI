package lab07.TextProcessor;

public class VowelFilter extends TextDecorator {

    protected VowelFilter(TextInterface t) {
        super(t);
    }

    @Override
    public String next() {
        return super.next().replaceAll("[AEIOUaeiou]", "");
    }
}
