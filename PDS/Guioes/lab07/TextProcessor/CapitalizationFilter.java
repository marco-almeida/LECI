package lab07.TextProcessor;

public class CapitalizationFilter extends TextDecorator {

    protected CapitalizationFilter(TextInterface t) {
        super(t);
    }

    @Override
    public String next() {
        String originalString = super.next();
        int length = originalString.length();
        if (length == 1) {
            return originalString.toUpperCase();
        }

        return Character.toUpperCase(originalString.charAt(0)) +
                originalString.substring(1, length - 1) +
                Character.toUpperCase(originalString.charAt(length - 1));
    }
}
