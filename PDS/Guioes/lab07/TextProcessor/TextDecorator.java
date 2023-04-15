package lab07.TextProcessor;

public abstract class TextDecorator implements TextInterface {
    protected TextInterface t;

    protected TextDecorator(TextInterface t) {
        this.t = t;
    }

    @Override
    public boolean hasNext() {
        return t.hasNext();
    }

    @Override
    public String next() {
        return t.next();
    }
}
