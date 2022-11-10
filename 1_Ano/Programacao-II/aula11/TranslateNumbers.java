import java.io.IOException;
import java.io.*;
import java.util.Scanner;
import p2utils.KeyValueList;

public class TranslateNumbers {
    public static void main(String[] args) throws IOException {

        KeyValueList<String> dict = new KeyValueList<String>();

        File fil = new File("numbers.txt");
        Scanner scf = new Scanner(fil);

        while (scf.hasNext()) {
            String value = scf.next();
            scf.next();
            String key = scf.next();
            dict.set(key, value);
        }
        scf.close();
        Scanner ler = new Scanner(System.in);

        System.out.println("Translate your numbers!");

        String traduzir = ler.nextLine();
        String[] frase = traduzir.split(" ");
        String newString = "";

        for (int i = 0; i < frase.length; i++) {
            if (dict.contains(frase[i])) {
                newString += dict.get(frase[i]) + " ";
            } else {
                newString += frase[i] + " ";
            }
        }
        System.out.println(newString);
        ler.close();
    }
}
