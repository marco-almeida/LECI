package aula10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class ex04 {
    public static void main(String[] args) throws IOException {
        List<String> moreThanTwoChars = new ArrayList<>();
        Scanner input = new Scanner(new FileReader("src/aula10/words.txt"));
        while (input.hasNext()) {
            String word = input.next();
            if (word.length() > 2) {
                moreThanTwoChars.add(word);
            }
            System.out.print(word + " ");
        }
        input.close();
        System.out.print("\nAcabam com 's': ");
        for (String string : moreThanTwoChars) {
            if (string.endsWith("s")) {
                System.out.print(string + " ");
            }
        }
        System.out.print("\nStrings que contêm carateres que não letras: ");
        Iterator<String> it = moreThanTwoChars.iterator();
        while (it.hasNext()) {
            if (!it.next().matches("[a-zA-Z]+")) {
                it.remove();
            }
        }
        System.out.println(moreThanTwoChars);
    }
}
