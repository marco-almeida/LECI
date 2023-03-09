import java.util.ArrayList;
import java.util.HashMap;

//grupo 02
//tentar usar mais funções java.nio (path em vez de file)


public class WSSolver
{
    public static void main(String[] args) {

        // first check to see if the program was run with the command line argument
        if(args.length < 1) {
            System.out.println("ERROR: missing one argument the file with the word solver");
            System.exit(1);
        }
        System.out.println("Begin");
        String filename = args[0];
        GetData test = new GetData(filename);
        Validate test2 = new Validate();
        Solver test3 = new Solver();

        Character[][] wSolver = test.getWordSolver();
        ArrayList<String> matrix = test.getMatrix();
        int nLines = test.getNlines(matrix)/2;
        int nColumns = test.getNColumns(matrix);
        ArrayList<String> wordList = test.getWordLst(); 

        if (test2.validatenow(nLines, nColumns, wordList)) {
            System.out.println("Matrix and words are valid");
        }
        else {
            System.out.println("Matrix and words are invalid");
            System.exit(1);
        }
        System.out.println("Solution:");
        HashMap<String, HashMap<Direction,ArrayList<Integer>>> solutions = new HashMap<String, HashMap<Direction,ArrayList<Integer>>>(); // Create a HashMap to store the words and their positions

        solutions = test3.wordSolver(wordList, wSolver, nLines, nColumns, Direction.LEFT);
        test3.printSolutions(solutions, nLines, nColumns);
    }
}