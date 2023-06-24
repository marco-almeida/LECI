package lab07.TextProcessor;

import java.text.Normalizer;

public class NormalizationFilter extends TextDecorator {

    protected NormalizationFilter(TextInterface t) {
        super(t);
    }

    @Override
    public String next() {
        String originalString = super.next();
        // remove diacritics
        String normalizedString = Normalizer.normalize(originalString, Normalizer.Form.NFD);
        // remove punctuation
        return normalizedString.replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{Punct}", "");
    }
}
