package aula12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ex01 {
    public static void main(String[] args) {
        int[] results = contadeiro();
        System.out.printf("Número Total de Palavras: %d\nNúmero de Diferentes Palavras: %d", results[0], results[1]);
    }

    public static int[] contadeiro() {
        int palavrasTotais = 0;
        List<String> palavras = new ArrayList<>();
        try (Scanner myReader = new Scanner(new File("src/aula12/words.txt"))) {
            while (myReader.hasNext()) {
                String palavra = myReader.next();
                palavrasTotais++;
                if (!palavras.contains(palavra)) {
                    palavras.add(palavra);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new int[] { palavrasTotais, palavras.size() };
    }
}
