package Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Read file line by line into List<String>
     */
    public static List<String> readFile(String path) {
        // reading text file into List in Java 7
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error while reading file");
            e.printStackTrace();
        }
        return lines;
    }
}
