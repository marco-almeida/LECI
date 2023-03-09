package lab01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SopaDeLetras {
    private List<String> fileLines;
    private Set<String> words;
    private List<List<Character>> puzzleCharacters;

    public SopaDeLetras(List<String> fileLines) {
        this.fileLines = fileLines;
        this.words = new LinkedHashSet<>();
        this.puzzleCharacters = new ArrayList<>();
        // always required to load words and puzzle into memory
        if (!this.validateFile()) {
            System.out.println("Invalid File");
            System.exit(2);
        }
    }

    public boolean validateFile() {
        return validatePuzzle() && validateWords();
    }

    public boolean validatePuzzle() {
        int puzzleSize = this.fileLines.get(0).length();

        // validate for maximum size of 40
        if (puzzleSize > 40) {
            System.out.println("Invalid puzzle size: " + puzzleSize);
            return false;
        }

        // iterate all puzzle lines
        for (int i = 0; i < puzzleSize; i++) {
            if (!validatePuzzleLine(this.fileLines.get(i), puzzleSize)) {
                return false;
            }
            // current String to char array, then to List<Character> and add it to
            // puzzleCharacters
            puzzleCharacters.add(
                    Arrays.asList(this.fileLines.get(i).chars().mapToObj(c -> (char) c).toArray(Character[]::new)));
        }
        return true;
    }

    public boolean validatePuzzleLine(String line, int intendedSize) {
        // check for valid size
        if (line.length() != intendedSize) {
            System.out.println("Invalid line size: " + line.length());
            return false;
        }
        // check if all characters in current line are uppercase
        for (int j = 0; j < intendedSize; j++) {
            if (!Character.isUpperCase(line.charAt(j))) {
                System.out.println("Character not uppercase: " + line.charAt(j));
                return false;
            }
        }
        return true;
    }

    public boolean validateWords() {
        int startingIndex = this.fileLines.get(0).length();
        // read all lines till end of file
        for (int i = startingIndex; i < this.fileLines.size(); i++) {
            // split line into words which are separated by commas, semicolons or spaces.
            String[] wordsInLine = this.fileLines.get(i).split("[,; ]");
            for (int j = 0; j < wordsInLine.length; j++) {
                if (!validateWord(wordsInLine[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validateWord(String word) {
        // check if word is valid
        if (!word.matches("[a-zA-Z]+")) {
            System.out.println("Word contains non alphabetical characters: " + word);
            return false;
        }
        // check if word is at least 3 characters long
        if (word.length() < 3) {
            System.out.println("Invalid word size: " + word.length());
            return false;
        }
        // check if word is not all uppercase
        if (word.equals(word.toUpperCase())) {
            System.out.println("Word is uppercase: " + word);
            return false;
        }
        this.words.add(word);

        // no caso de farol e faro, tirar faro do set, uma vez que farol contains faro
        boolean removeMyself = false;
        Iterator<String> iterator;
        for (iterator = words.iterator(); iterator.hasNext();) {
            String w = iterator.next();
            if (w.toLowerCase().contains(word.toLowerCase()) && !w.equalsIgnoreCase(word)) {
                removeMyself = true;
            }
        }

        if (removeMyself) {
            iterator.remove();
        }

        return true;
    }

    public Solution findWord(String word) throws Exception {
        String originalWord = word;
        word = word.toUpperCase();
        List<Solution> wordSolutions = new ArrayList<>();

        for (int line = 0; line < this.puzzleCharacters.size(); line++) {
            for (int column = 0; column < this.puzzleCharacters.get(line).size(); column++) {
                // check if character is the same as the first character of the word
                if (this.puzzleCharacters.get(line).get(column) == word.charAt(0)) {
                    // check if word is in horizontal direction
                    String horizontalResult = searchHorizontal(word, line, column);
                    if (!horizontalResult.equals("")) {
                        wordSolutions
                                .add(new Solution(new int[] { line + 1, column + 1 }, originalWord, horizontalResult));
                    }
                    // check if word is in vertical direction
                    String verticalResult = searchVertical(word, line, column);
                    if (!verticalResult.equals("")) {
                        wordSolutions
                                .add(new Solution(new int[] { line + 1, column + 1 }, originalWord, verticalResult));
                    }
                    // check if word is in diagonal direction
                    String diagonalResult = searchDiagonal(word, line, column);
                    if (!diagonalResult.equals("")) {
                        wordSolutions
                                .add(new Solution(new int[] { line + 1, column + 1 }, originalWord, diagonalResult));
                    }
                }
            }
        }
        if (wordSolutions.isEmpty()) {
            String error = String.format("Word %s not found in puzzle\n", originalWord);
            throw new Exception(error);
        }
        if (wordSolutions.size() > 1) {
            String error = String.format("Word %s found multiple times in puzzle\n", originalWord);
            throw new Exception(error);
        }
        return wordSolutions.get(0);
    }

    public String searchHorizontal(String word, int line, int column) {
        // word must be uppercase

        // check if word is in the right side
        int i;
        int columnValue = column; // temp value so as not to modify column variable
        for (i = 0; columnValue < this.puzzleCharacters.get(line).size() && i < word.length(); columnValue++, i++) {
            char puzzleChar = this.puzzleCharacters.get(line).get(columnValue);
            if (word.charAt(i) != puzzleChar) {
                break;
            }
        }

        // if we get here
        if (i == word.length()) {
            return "Right";
        }

        // check if word is in the left side
        for (i = 0; column >= 0 && i < word.length(); column--, i++) {
            char puzzleChar = this.puzzleCharacters.get(line).get(column);
            if (word.charAt(i) != puzzleChar) {
                break;
            }
        }

        // if we get here
        if (i == word.length()) {
            return "Left";
        }

        return ""; // no result found
    }

    public String searchVertical(String word, int line, int column) {
        // word must be uppercase

        // check if word is in the upper side
        int i;
        int lineValue = line; // temp value so as not to modify line variable
        for (i = 0; lineValue >= 0 && i < word.length(); lineValue--, i++) {
            char puzzleChar = this.puzzleCharacters.get(lineValue).get(column);
            if (word.charAt(i) != puzzleChar) {
                break;
            }
        }

        // if we get here
        if (i == word.length()) {
            return "Up";
        }

        // Check if word is in the bottom side
        for (i = 0; line < this.puzzleCharacters.size() && i < word.length(); line++, i++) {
            char puzzleChar = this.puzzleCharacters.get(line).get(column);
            if (word.charAt(i) != puzzleChar) {
                break;
            }
        }

        // if we get here
        if (i == word.length()) {
            return "Bottom";
        }

        return "";

    }

    public String searchDiagonal(String word, int line, int column) {
        // word must be uppercase

        int i;
        int lineValue = line;
        int columnValue = column; // temp value so as not to modify column/line variable

        // check if word is in the upper left side
        for (i = 0; lineValue >= 0 && columnValue >= 0 && i < word.length(); lineValue--, columnValue--, i++) {
            char puzzleChar = this.puzzleCharacters.get(lineValue).get(columnValue);
            if (word.charAt(i) != puzzleChar) {
                break;
            }
        }

        // if we get here
        if (i == word.length()) {
            return "UpLeft";
        }

        // reset values
        lineValue = line;
        columnValue = column;

        // check if word is in the upper right side
        for (i = 0; lineValue >= 0 && columnValue < this.puzzleCharacters.size()
                && i < word.length(); lineValue--, columnValue++, i++) {
            char puzzleChar = this.puzzleCharacters.get(lineValue).get(columnValue);
            if (word.charAt(i) != puzzleChar) {
                break;
            }
        }

        // if we get here
        if (i == word.length()) {
            return "UpRight";
        }

        // reset values
        lineValue = line;
        columnValue = column;

        // check if word is in the bottom left side
        for (i = 0; lineValue < this.puzzleCharacters.size() && columnValue >= 0
                && i < word.length(); lineValue++, columnValue--, i++) {
            char puzzleChar = this.puzzleCharacters.get(lineValue).get(columnValue);
            if (word.charAt(i) != puzzleChar) {
                break;
            }
        }

        // if we get here
        if (i == word.length()) {
            return "BottomLeft";
        }

        // reset values
        lineValue = line;
        columnValue = column;

        // check if word is in the bottom right side
        for (i = 0; lineValue < this.puzzleCharacters.size() && columnValue < this.puzzleCharacters.size()
                && i < word.length(); lineValue++, columnValue++, i++) {
            char puzzleChar = this.puzzleCharacters.get(lineValue).get(columnValue);
            if (word.charAt(i) != puzzleChar) {
                break;
            }
        }

        // if we get here
        if (i == word.length()) {
            return "BottomRight";
        }

        return "";
    }

    public List<Solution> solvePuzzle() {
        try {
            List<Solution> solutions = new ArrayList<>();
            for (String word : this.words) {
                solutions.add(findWord(word));
            }
            return solutions;
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }

    }

    public String getResults() {
        // first part of the output
        StringBuilder stb = new StringBuilder();
        List<Solution> solutions = solvePuzzle();
        for (Solution s : solutions) {
            stb.append(s);
        }
        stb.append("\n");

        // second part of the output

        List<List<Character>> solvedPuzzleStr = new ArrayList<>();
        // fill array with dots
        for (int i = 0; i < this.puzzleCharacters.size(); i++) {
            List<Character> line = new ArrayList<>();
            for (int j = 0; j < this.puzzleCharacters.size(); j++) {
                line.add('.');
            }
            solvedPuzzleStr.add(line);
        }

        for (int i = 0; i < this.puzzleCharacters.size(); i++) {
            for (int j = 0; j < this.puzzleCharacters.size(); j++) {
                // check if this index matches starting index of any solution
                for (Solution s : solutions) {
                    int[] startingCoordinates = s.getStartingCoordinates();
                    if (startingCoordinates[0] == i + 1 && startingCoordinates[1] == j + 1) {
                        // if it matches, add the word to the array
                        String word = s.getWord();
                        String direction = s.getDirection();
                        int lineValue = i;
                        int columnValue = j;
                        for (int k = 0; k < word.length(); k++) {
                            solvedPuzzleStr.get(lineValue).set(columnValue, word.charAt(k));
                            // first index should be the first letter of the word
                            // next letter of the world depends on the direction
                            switch (direction) {
                                case "Left":
                                    columnValue--;
                                    break;
                                case "Right":
                                    columnValue++;
                                    break;
                                case "Up":
                                    lineValue--;
                                    break;
                                case "Bottom":
                                    lineValue++;
                                    break;
                                case "UpLeft":
                                    lineValue--;
                                    columnValue--;
                                    break;
                                case "UpRight":
                                    lineValue--;
                                    columnValue++;
                                    break;
                                case "BottomLeft":
                                    lineValue++;
                                    columnValue--;
                                    break;
                                case "BottomRight":
                                    lineValue++;
                                    columnValue++;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        }

        for (List<Character> line : solvedPuzzleStr) {
            for (Character c : line) {

                stb.append(Character.toUpperCase(c) + " ");
            }
            stb.append("\n");
        }

        return stb.toString();
    }

    @Override
    public String toString() {
        return "Sopa de Letras\nOriginal File:\n" + this.fileLines + "\nPuzzle chars\n" + this.puzzleCharacters + "\nWords:\n"
                + this.words;
    }

}
