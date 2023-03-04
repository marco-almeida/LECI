package guiao01;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ex06 {
    public static void main(String[] args) {
        // java src/guiao01/Ex06.java src/guiao01/bloco1/dic1.txt
        // src/guiao01/bloco1/texto1.txt src/guiao01/bloco1/texto2.txt
        if (args.length < 2) {
            System.out.println("Usage: java Ex06 <correspondence.txt> <file.txt> [more files.txt]");
            System.exit(1);
        }
        Map<String, List<String>> correspondence = readFileToMap(args[0]);
        if (correspondence.isEmpty()) {
            System.out.println("Error reading correspondence file");
            System.exit(1);
        }

        for (String filePath : Arrays.copyOfRange(args, 1, args.length)) {
            List<String> fileContent = readFile(filePath);
            if (!fileContent.isEmpty())
                translateStringList(correspondence, readFile(filePath)).forEach(System.out::println);
        }
    }

    public static List<String> readFile(String filepath) {
        Path path = Paths.get(filepath);
        try {
            return Files.readAllLines(path);
        } catch (IOException ex) {
            ex.printStackTrace();// handle exception here
            return List.of();
        }
    }

    public static List<String> translateStringList(Map<String, List<String>> correspondence, List<String> list) {
        List<String> newList = new ArrayList<>(list.size());
        // iterate file as a list of strings
        for (String phrase : list) {
            StringBuilder stb = new StringBuilder();
            // iterate first line
            for (String word : phrase.split(" ")) {
                List<String> translation = correspondence.getOrDefault(word, Arrays.asList(word));
                // add translation to new string
                for (String newword : translation) {
                    stb.append(newword + " ");
                }
            }
            // remove last space
            newList.add(stb.toString().trim().replaceAll(" +", " "));
        }
        return newList;
    }

    public static Map<String, List<String>> readFileToMap(String filePath) {
        Path path = FileSystems.getDefault().getPath(filePath);
        try {
            // for each String, split(" ") and add [0] as key and the rest as value(list<String>)
            return Files.lines(path)
                    .collect(Collectors.toMap(k -> k.split(" ")[0],
                            v -> Arrays.asList(v.split(" ")).subList(1, v.split(" ").length)));
        } catch (Exception e) {
            System.out.println("Error reading file\n" + e.initCause(e));
            return Map.of();
        }
    }
}
