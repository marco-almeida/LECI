
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WSSolver {
  public static void main(String[] args) {
    String file = args[0];
    
    List<ArrayList<String>> data = readData(file);

    List<ArrayList<String>> puzzle = data.subList(0, data.size() - 1);
    List<String> words = data.get(data.size() - 1);

    Validation validation = new Validation(puzzle, words);
    validation.MaximumSize(puzzle); // Requisito de entrada 1
    validation.PuzzleInUpperCase(puzzle); // Requisito de entrada 2
    validation.WordsinLowerCaseOrBoth(words); // Requisito de entrada 3
    validation.WordsAlphaChars(words); // Requisito de entrada 4
    validation.AtLeast3Characters(words);  // Requisito de entrada 7
    words= validation.BiggerWordWithSamePrefix(words); // Requisito de entrada 9

    Solver solver = new Solver(puzzle, words);
    solver.solve(); 

    // print do puzzle
    //String printPuzzle = solver.toStringPuzzle();
    //System.out.println(printPuzzle);

    // print das palavras contidas no .txt
    //String printWords = solver.toStringWords();
    //System.out.println(printWords);

    //System.out.println();
    
    //print da informação de todas as palavras
    String printWordsResults = solver.toStringResults();
    System.out.println(printWordsResults);

    //print do puzzle final
    solver.makefinalPuzzle();
    String printFinalPuzzle= solver.toStringFinalPuzzle();
    System.out.println(printFinalPuzzle);
    
  }

  public static List<ArrayList<String>> readData(String file){

    List<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
    String linha = "";
    List<ArrayList<String>> puzzle = new ArrayList<ArrayList<String>>();
    List<String> subPuzzle = new ArrayList<String>();
    List<String> words = new ArrayList<String>();

    try {
      FileReader arq = new FileReader(file);
      BufferedReader lerArq = new BufferedReader(arq);
      String linha1 = lerArq.readLine(); // lê a primeira linha do arquivo
      for (int i = 0; i < linha1.length(); i++) {
        subPuzzle.add(linha1.substring(i, i + 1));
      }
      puzzle.add((ArrayList<String>) subPuzzle);

      for (int count = 1; count < linha1.length(); count++) {
        linha = lerArq.readLine(); // lê da segunda até a última linha
        if(linha.isEmpty()){ // Requisito de entrada 5
          System.out.println("Existem linhas vazias no ficheiro.");
          System.exit(0); // sai do programa
        }

        subPuzzle = new ArrayList<String>();
        for (int i = 0; i < linha.length(); i++) {
          subPuzzle.add(linha.substring(i, i + 1));
        }
        puzzle.add((ArrayList<String>) subPuzzle);
      }

      while ((linha = lerArq.readLine()) != null) {
        if(linha.isEmpty()){ // Requisito de entrada 5
          System.out.println("Existem linhas vazias no ficheiro.");
          System.exit(0); // sai do programa
        }
        for (String s : linha.split("[;, ]")) { // Requisito de entrada 6
            words.add(s);
        }
    }

      arq.close();
    } catch (IOException e) {
      System.err.printf("Erro na abertura do arquivo: %s.\n",
          e.getMessage()); // aqui também podia ser System.out.println("Erro na abertura do arquivo: ");
    }

    data.addAll(puzzle);
    data.add((ArrayList<String>) words);

    return data;
  } 
}
