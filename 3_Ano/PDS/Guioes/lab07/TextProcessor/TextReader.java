package lab07.TextProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TextReader implements TextInterface {

    private final List<String> content;

    public TextReader(String fileName) {
        this.content = readFile(fileName);
    }

    @Override
    public boolean hasNext() {
        return !content.isEmpty();
    }

    @Override
    public String next() {
        String res = content.get(0);
        content.remove(0);
        return res;
    }

    private List<String> readFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
