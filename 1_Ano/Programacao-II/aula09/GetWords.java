import static java.lang.System.*;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

import static p2utils.Sorting.*;

public class GetWords {
  public static void main(String[] args) throws IOException {
    if (args.length < 1) {
      err.println("Usage: java GetWords <file>");
      exit(1);
    }

    String[] words = extractWords(args);
    int i = 0;

    for (i = 0; i < words.length; i++)
      out.printf("[%05d] %s\n", i + 1, words[i]);

    out.printf("Palavras distintas: %d", i);
    File fil = new File("words.txt");
    PrintWriter pwf = new PrintWriter(fil);
    for (String element : words) {
      pwf.println(element);
    }
    pwf.close();
  }

  // Read words from a file, return words in an array
  static String[] extractWords(String[] args) throws IOException {
    File[] fin = new File[args.length];

    for (int i = 0; i < args.length; i++) {
      fin[i] = new File(args[i]);
    }

    int amount = 0;

    // Loop para descobrir amount
    for (int i = 0; i < args.length; i++) {
      if (fin[i].isFile()) {
        Scanner scf = new Scanner(fin[i]).useDelimiter("[\\p{Punct}\\p{Space}]+");
        while (scf.hasNext()) {
          scf.next();
          amount++;
        }
        scf.close();
      }
    }

    String arr1[] = new String[amount];
    int amount2 = 0;

    for (int i = 0; i < args.length; i++) {
      if (fin[i].isFile()) {
        Scanner scf = new Scanner(fin[i]).useDelimiter("[\\p{Punct}\\p{Space}]+");
        while (scf.hasNext()) {
          String a = scf.next();
          if (!check(arr1, a)) {
            arr1[amount2] = a;
            amount2++;
          }
        }
        scf.close();
      }
    }

    String result[] = new String[amount2];
    for (int i = 0; i < amount2; i++) {
      result[i] = arr1[i];
    }

    mergeSort(result, 0, amount2);
    return result;
  }

  public static boolean check(String[] arr, String toCheckValue) {
    // check if the specified element
    // is present in the array or not
    // using Linear Search method
    boolean test = true;

    for (String element : arr) {
      if (element == null) {
        return false;
      }

      if (element.equals(toCheckValue)) {
        return true;
      }
    }

    return test;
  }
}
