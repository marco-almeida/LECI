package lab01;

import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Utils.Utils;

public class WSGenerator{
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java lab01/WSGenerator [filename.txt]");
            System.out.println("Usage: java lab01/WSGenerator [filename.txt] [size]");
            System.exit(1);
        }
        if (args.length > 1 && Integer.valueOf(args[1]) > 40){
            System.out.println("Error: Size can not exceed 40");
            System.exit(1);
        }

        String filename = args[0];
        List<String> fileLines = Utils.readFile(filename);

        for(int i=0; i<fileLines.size(); i++){
            boolean validWord = validateWord(fileLines.get(i));
            if (validWord == false){
                System.exit(1);
            }
        }

        int puzzleSize = 12;
        if (args.length > 1)
            puzzleSize = Integer.valueOf(args[1]);

        List<List<Character>> puzzle = GeneratorUtils.generatePuzzle(fileLines, puzzleSize);
        System.out.println();
        //List<List<Character>> puzzle = GeneratorUtils.generateCharMatrix(12);
        GeneratorUtils.printPuzzle(puzzle);
        System.out.println();

        if (args.length == 3){
            String fileName = args[2];
            writePuzzle(fileName, puzzle);
        }

    }

    public static void writePuzzle(String fileName, List<List<Character>> puzzle){
        try {
            File writeFile = new File(fileName);
            if (writeFile.createNewFile())
              System.out.println("File created: " + writeFile.getName());
            else 
              System.out.println("File already exists.");
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(fileName);
            int puzzleSize = puzzle.size();
            for(int i=0; i<puzzleSize; i++){
                for(int j=0; j<puzzleSize; j++){
                    myWriter.write(puzzle.get(i).get(j));
                }
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static boolean validateWord(String word) {
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
        return true;
    }
}
