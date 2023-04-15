import java.util.*;

public class Validate {

    // validate the matrix and the words
    public boolean validatenow(int nlines, int ncolumns, ArrayList<String> wordList) {
        // private int nlines, ncolumns;
        // private String filename;
        // private ArrayList<String> wordlst, matrix, allWords;
        // private Character[][] wordSolver;
        boolean valid = true;   

        System.out.println("Validating matrix and words...");        
           
        // matrix validation
        System.out.println ("nlines: " + nlines + " ncolumns: " + ncolumns);
        if (nlines != ncolumns || nlines > 40) {
            valid = false;
            System.out.println("ERROR: The matrix is invalid");
            System.exit(1);
        }

        // words validation
        for (String s : wordList) {
            for (int i=0; i < s.length(); i++) {
                if ( !Character.isLowerCase(s.charAt(i)) || !Character.isLetter(s.charAt(i)) || s.length() < 3 ) {
                    // if is in upper case                 // if is letter                     // if len < 3
                    valid = false;
                    System.out.println("ERROR: The word " + s + " is invalid");
                    // System.exit ???
                }
            }
        }
        //if words contains words, remove the smaller word
        for (int i=0; i < wordList.size(); i++) {
            for (int j=0; j < wordList.size(); j++) {
                if (wordList.get(i).contains(wordList.get(j)) && wordList.get(i).length() > wordList.get(j).length()) {
                    wordList.remove(j);
                }
            }
        }
        // check if words are repeated
        int count;
        for (int i=0; i < wordList.size(); i++) {
            count = 0;
            for (int j=0; j < wordList.size(); j++) {
                if (wordList.get(i).equals(wordList.get(j))) {
                    count++;
                }
            }
            if (count > 1) {
                valid = false;
                System.out.println("ERROR: The word " + wordList.get(i) + " is repeated");
                // System.exit ???
            }
        }

        return valid;
    }
    
}
