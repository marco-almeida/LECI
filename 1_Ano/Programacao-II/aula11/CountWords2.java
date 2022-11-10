import static java.lang.System.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import p2utils.SortedKeyValueList;

public class CountWords2 {

  public static void main(String[] args) {
    SortedKeyValueList<Integer> counts = new SortedKeyValueList<>();

    for (int a = 0; a < args.length; a++) { // Processa cada ficheiro
      File fin = new File(args[a]);
      // Using a try-with-resources:
      try (Scanner scf = new Scanner(fin)) {
        while (scf.hasNextLine()) { // Processa cada linha
          String line = scf.nextLine();
          // Divide a linha em "palavras", considerando como separador
          // qualquer sequência de 1 ou mais carateres não alfabéticos:
          String[] palavras = line.split("[^\\p{IsAlphabetic}]+");
          // (Sobre expressões regulares usadas no split:
          // https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html)

          for (int i = 0; i < palavras.length; i++) { // Processa cada palavra
            // Completar...
            if (counts.contains(palavras[i].toLowerCase())){
              int valor = counts.get(palavras[i].toLowerCase());
              counts.set(palavras[i].toLowerCase(), valor+1);
            } else {
              counts.set(palavras[i].toLowerCase(), 1);
            }
          }
        }
      } catch (Exception e) {
        err.println("ERRO: " + e.getMessage());
      }
    }
    out.println(counts.toString("Results:\n", ";\n", "\nEnd"));
    out.println(counts.toString()); // mesma lista, outro formato
    mostFrequent(counts);
  }

  // Find and print the key with most occurrences
  // and its relative frequency.
  static void mostFrequent(SortedKeyValueList<Integer> counts) {
    // Completar...
    double max = 0;
    String mostUsedKey = "";
    int n = 0;
    for (String a : counts.keys()){
      if (counts.get(a) > max){
        max = counts.get(a);
        mostUsedKey = a;
      }
      n += counts.get(a);
    }
    System.out.print("Most frequent key is <"+ mostUsedKey +"> with a relative frequency of "+ max / n * 100+"%");
  }
}
