/**
 * ta feito assim porque nao consegui ler bem o ficheiro tive que usar deepweb scanner
 */
package aula11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.nio.charset.StandardCharsets;

public class ex01 {
    public static void main(String[] args) {
        final char[] seperator = { '\t', '\n', '.', ':', '‘', '’', ';', '?', '!', '-', '*', '{', '}', '=', '+', '&',
                '/', '(', ')', '[', ']', '”', '“', '"', '\'', '/', ' ', ',' };
        Map<String, Map<String, Integer>> dados = new TreeMap<>();
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("src/aula11/aula11_files/major.txt"),
                StandardCharsets.UTF_8))) {
            int c;
            StringBuilder stb = new StringBuilder();
            String lastWord = null;
            while ((c = reader.read()) != -1) {
                // reads one char
                char character = (char) c;
                for (int i = 0; i < seperator.length; i++) {
                    // checks if char is separator
                    if (character == seperator[i]) {
                        String currentWord = stb.toString().toLowerCase();
                        // add word to data base
                        if (currentWord.length() >= 3) {
                            words.add(currentWord);
                            // start adding words to database after second element
                            if (words.size() != 1) {
                                Map<String, Integer> temp = new HashMap<>();
                                temp.put(currentWord, 1);
                                // add lastWord if doesnt already exist & add currentWord key if doesnt exist
                                if (dados.putIfAbsent(lastWord, temp) != null
                                        && dados.get(lastWord).putIfAbsent(currentWord, 1) != null) {
                                    dados.get(lastWord).put(currentWord, dados.get(lastWord).get(currentWord) + 1);
                                }
                            }
                            lastWord = currentWord;
                        }
                        stb.setLength(0);
                        break;
                    }
                    // keep adding char to word if no separators found
                    if (i == seperator.length - 1) {
                        stb.append(character);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dados);
    }
}
