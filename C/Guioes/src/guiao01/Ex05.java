package guiao01;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ex05 {
    public static void main(String[] args) {
        String filePath = "src/guiao01/bloco1/numbers.txt";
        Map<String, Integer> map = readFileToMap(filePath);
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        input.close();
        System.out.println(translateStringToNumber(userInput, map));
    }

    public static Map<String, Integer> readFileToMap(String filePath) {
        Path path = FileSystems.getDefault().getPath(filePath);
        try {
            return Files.lines(path)
                    .filter(s -> s.matches("^\\w+ - \\w+"))
                    .collect(Collectors.toMap(k -> k.split(" - ")[1], v -> Integer.parseInt(v.split(" - ")[0])));
        } catch (Exception e) {
            System.out.println("Error reading file\n" + e.initCause(e));
            System.exit(1);
        }
        return Map.of();
    }

    public static int translateStringToNumber(String originalString, Map<String, Integer> dict) {
        Deque<Integer> consideringNumbers = new ArrayDeque<>();
        int total = 0;

        for (String word : originalString.split(" ")) {
            int number = dict.getOrDefault(word, 0);
            if (consideringNumbers.isEmpty() || consideringNumbers.peek() < number) {
                consideringNumbers.push(number);
            } else {
                List<Integer> subtotal = clearDequeAndGetElements(consideringNumbers);
                total += subtotal.stream().reduce(1, (a, b) -> a * b);
                consideringNumbers.push(number);
            }
        }
        List<Integer> subtotal = clearDequeAndGetElements(consideringNumbers);
        total += subtotal.stream().reduce(1, (a, b) -> a * b);
        return total;
    }

    public static <T> List<T> clearDequeAndGetElements(Deque<T> deque) {
        List<T> elements = new ArrayList<>();
        while (!deque.isEmpty()) {
            elements.add(deque.pop());
        }
        return elements;
    }
}
