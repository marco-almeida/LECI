import java.util.ArrayList;
import java.util.List;

public class Validation {

    List<ArrayList<String>> puzzle;
    List<String> words;

    public Validation(List<ArrayList<String>> puzzle, List<String> words) {
        this.puzzle = puzzle;
        this.words = words;
    }

    public Validation(List<String> words) { // Construtor para usar na parte do generator
        this.words = words;
    }

    public void MaximumSize(List<ArrayList<String>> puzzle){ // Requisito de entrada 1
        int Ncols= 0;
        if(puzzle.size() > 40){
            System.out.println("O puzzle tem de ter no máximo 40 linhas.");
            System.exit(0);
        }
        for (int i = 0; i < puzzle.size(); i++) {
            if(puzzle.get(i).size() > 40){
                System.out.println("O puzzle tem de ter no máximo 40 colunas.");
            System.exit(0);
            }
            Ncols= i;
        }
        if(puzzle.size() != puzzle.get(Ncols).size()){
            System.out.println("O puzzle tem de ser quadrado.");
            System.exit(0);
        }
    }

    public void PuzzleInUpperCase(List<ArrayList<String>> puzzle){ // Requisito de entrada 2
        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(i).size(); j++) {
                if (puzzle.get(i).get(j).equals(puzzle.get(i).get(j).toLowerCase())) {
                    System.out.println("O puzzle tem de conter apenas letras maiúsculas.");
                    System.exit(0);
                }
            }
        }
    }

    public void WordsinLowerCaseOrBoth(List<String> words){ // Requisito de entrada 3
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).equals(words.get(i).toUpperCase())) {
                System.out.println("As palavras têm de conter apenas letras minúsculas ou ambas.");
                System.exit(0);
            }
        }
    }

    public void WordsAlphaChars(List<String> words){ // Requisito de entrada 4
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.get(i).length(); j++) {
                if (!Character.isLetter(words.get(i).charAt(j))) {
                    System.out.println("As palavras têm de conter apenas caracteres alfabéticos.");
                    System.exit(0);
                }
            }
        }
    }

    public void AtLeast3Characters(List<String> words){ // Requisito de entrada 7
        for (int i = 0; i < words.size(); i++) {
            if(words.get(i).length() < 3){
                System.out.println("As palavras têm de conter pelo menos 3 carateres.");
                System.exit(0); // parar a execução do programa
            }
        }
    }
    
    public List<String> BiggerWordWithSamePrefix(List<String> words) {// Requisito de entrada 9
        // Esta função é passível de ser otimizada se a lista for ordenada, pois diminui o nº de comparações
        List<String> result = new ArrayList<String>();
      
        for (int i = 0; i < words.size(); i++) {
          boolean hasBigger = false;
          for (int j = 0; j < words.size(); j++) {
            if (i != j && words.get(j).contains(words.get(i))) {
                if(words.get(i).equals(words.get(j))){
                    System.out.println("Existem palavras repetidas na lista.");
                    System.exit(0);
                }
              hasBigger = true;
              break;
            }
        }
          if (!hasBigger) {// Como não há maior palavra com o mesmo prefixo, adiciona-se a palavra à lista
            result.add(words.get(i));
          }
        }
        return result;
      }
      
}
