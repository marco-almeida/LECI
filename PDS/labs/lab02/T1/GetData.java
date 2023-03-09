import java.io.*;
import java.util.*;

public class GetData {

    private int nlines, ncolumns;
    private String filename;
    private ArrayList<String> wordlst, matrix, allWords;
    private Character[][] wordSolver;

    // Constructor
    public GetData(String filename) {
        this.filename = filename;  
        wordlst = new ArrayList<String>(); // Inicialize wordlst ArrayList
        matrix = new ArrayList<String>(); // Inicialize matrix ArrayList
        allWords = new ArrayList<String>(); // Inicialize allWords ArrayList
    }

    public String getFilename() {
        return filename; 
    }

    public void setFilename(String fn) {
        filename = fn;
    }

    public ArrayList<String> getAllWords() {
        try {
            
            File file = new File(getFilename()); // Open the file to read it
            Scanner reader = new Scanner(file); // Open the file to read it

            while(reader.hasNext()) {

                String next = reader.nextLine(); // next é declarado como a linha que está sendo lida

                if (next.isEmpty()) { // Verifica se a linha está vazia
                    System.out.println("ERROR: The file has an empty line");
                    System.exit(1);
                }

                allWords.add(next);
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found");
            System.exit(1);
        }

        return allWords;
    }

    public ArrayList<String> getMatrix() {
        ArrayList<String> allWords = getAllWords(); // Get all the words from the file

        for(int i = 0; i < allWords.size(); i++) { // For each line in the file
            if (Character.isUpperCase(allWords.get(i).charAt(0)))  // If the letter is uppercase
                matrix.add(allWords.get(i)); // Add the line to the matrix
            else // If the letter is not uppercase
                break; // Break the loop and go to the next line    
        }

        return matrix;
    }

    public Character[][] getWordSolver() {
        
        ArrayList<String> matrix = getMatrix(); // Get the matrix from the file

        int nLines = getNlines(matrix); // Get the number of lines in the matrix
        // System.out.println("Number of lines: " + nLines);
        int nColumns = getNColumns(matrix); // Get the number of columns in the matrix
        // System.out.println("Number of columns: " + nColumns);

        wordSolver = new Character[nLines][nColumns];

        for (int i = 0; i < nLines; i++) { // For each line in the matrix
            for (int j = 0; j < nColumns; j++) { // For each column in the matrix
                wordSolver[i][j] = matrix.get(i).charAt(j); // Add the letter to the matrix
            }
        }

        // System.out.println("WordSolver: " );

        // Print thw wordSolver matrix with each element in a new line
        for (int i = 0; i < nLines; i++) { // For each line in the matrix
            for (int j = 0; j < nColumns; j++) { // For each column in the matrix
                System.out.print(wordSolver[i][j] + " "); // Print the letter
            }
            System.out.println(); // Print a new line
        }

        return wordSolver;

    }

    public ArrayList<String> getWordLst() {

        List<String> wordlst_temp = new ArrayList<String>();
        ArrayList<String> allWords = getAllWords(); // Get all the words from the file
        
        //get the words with lowercase from arraylist allWords
        for(int x = 0; x < allWords.size()/2; x++) { // For each line in the file
            if (Character.isUpperCase(allWords.get(x).charAt(0))) { // If the letter is uppercase
                continue; // Continue to the next element in the loop
            } else if (Character.isLowerCase(allWords.get(x).charAt(0))) {  // If the first letter is in lowercase
                wordlst_temp.add(allWords.get(x)); // Add the line to the wordlst_temp
            } else  // If the letter is not uppercase or lowercase
                break;
        }

        //Split the words in wordlst_temp with delimiters space , and ; and add them to wordlst
        for(int i = 0; i < wordlst_temp.size(); i++) { // For each line in the wordlst_temp
            String[] words = wordlst_temp.get(i).split("[ ,;]+"); // Split the line in words
            for(int j = 0; j < words.length; j++) { // For each word in the line
                wordlst.add(words[j]); // Add the word to the wordlst
            }
        }

        // System.out.println("WordLst:\n" + wordlst);

        return wordlst;
        
    }

    public int getNlines(ArrayList<String> matrix) {
        nlines = matrix.size(); // Get number of lines which is the number of elements in the Array list matrix
        return nlines;
    }

    public int getNColumns(ArrayList<String> matrix) {

        ncolumns = matrix.get(0).length(); // Get the size of the first line in the matrix

        // See if all the lines have the same number of letters
        ArrayList<Integer> line_sizes = new ArrayList<Integer>();

        // Add to the ArrayList created the size of each line
        for(int j = 0; j < matrix.size(); j++) 
            line_sizes.add(matrix.get(j).length());

        // See if the lines have all the same size by converting the ArrayList to an HashSet where you can't have repeted items
        HashSet<Integer> matrix_columns = new HashSet<Integer>(line_sizes);

        if(matrix_columns.size() == 1)
            ncolumns = line_sizes.get(0);
        else {
            System.out.println("ERROR: there are lines that have more letters than others");
            System.exit(1);
        }
        return ncolumns;
    }   

}
