package guiao01;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ex04 {
    public static void main(String[] args) {
        String filePath = "src/guiao01/bloco1/numbers.txt";
        Map<String, String> map = readFileToMap(filePath);
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        input.close();
        System.out.println(replaceInstancesInString(userInput, map));
    }

    public static Map<String, String> readFileToMap(String filePath) {
        Path path = FileSystems.getDefault().getPath(filePath);
        try {
            return Files.lines(path)
                    .filter(s -> s.matches("^\\w+ - \\w+"))
                    .collect(Collectors.toMap(k -> k.split(" - ")[1], v -> v.split(" - ")[0]));
        } catch (Exception e) {
            System.out.println("Error reading file\n" + e.initCause(e));
            System.exit(1);
        }
        return Map.of();
    }

    public static String replaceInstancesInString(String originalString, Map<String, String> dict) {
        for (Map.Entry<String, String> entry : dict.entrySet()) {
            originalString = originalString.replaceAll(entry.getKey(), entry.getValue());
        }
        return originalString;
    }
}
