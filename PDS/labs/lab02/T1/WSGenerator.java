import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class WSGenerator {

    public static void main(String[] args) {

        switch (args.length) {

            case 0:
                System.out.println("ERROR: no arguments");
                break;

            case 1:
                System.out.println("ERROR: 1 argument");
                break;

            case 2:
                System.out.println("ERROR: 2 arguments");
                break;

            case 3:
                System.out.println("ERROR: 3 arguments");
                break;

            case 4:
                if (args[0].equals("-i") && args[1].endsWith(".txt") && args[2].equals("-s") && isNumeric(args[3])) {
                    generate(args[1], "wordlist_result.txt", Integer.parseInt(args[3])); // nome do ficheiro predefinido
                } else {
                    System.out.println("ERROR: invalid arguments");
                }
                break;

            case 5:
                System.out.println("ERROR: 5 arguments");
                break;

            case 6:
            if (args[0].equals("-i") && args[1].endsWith(".txt") && args[2].equals("-s") && isNumeric(args[3]) && args[4].equals("-o") && args[5].endsWith(".txt")) {
                generate(args[1], args[5], Integer.parseInt(args[3]));
            } else {
                System.out.println("ERROR: invalid arguments");
            }
            break;
            
            default:
                System.out.println("ERROR: too many arguments");
                break;
        }
    }

    // read from file1, write to file2 what is in file1
    public static void generate (String file1, String file2, int args) {
        ArrayList<String> wordlst = new ArrayList<String>();
        HashMap<String,Direction> wordsInfo = new HashMap<String,Direction>();
        ArrayList<String> sep = new ArrayList<String>();
        sep.add(" "); 
        sep.add(";");
        sep.add(",");   
        sep.add("\n");
        try {
            File file = new File(file1);
            Scanner reader = new Scanner(file);
            
            while(reader.hasNext()) {
                String next = reader.nextLine();
                if (next.isEmpty()) {
                    System.out.println("ERROR: The file has an empty line");
                    System.exit(1);
                }
                wordlst.add(next);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found");
            System.exit(1);
        }

        int max = args;

        Character[][] grid = new Character[max][max];

        // random directions to each word on the wordlist
        for(String str : wordlst) {
            if (str.length() > max) {
                System.out.println("ERROR: word too long (max = " + max + " for word " + str + ")");
                System.exit(1);
            } else {
                wordsInfo.put(str, Direction.getRandomDirection());
            }

        }
        // Fill the grid with random characters
        for (int i = 0; i < max; i++) { 
            for (int j = 0; j < max; j++) {
                int randomChar = (int) (Math.random() * 26 + 65);
                grid[i][j]= ((char) randomChar);
            }
        }

        // for each word in array, random line and column form 0 to args
        for (String word: wordsInfo.keySet()) { 
            Direction dir = wordsInfo.get(word); // get the direction of the word
            word = word.toUpperCase();
            int randomLine = (int) (Math.random() * max);
            int randomColumn = (int) (Math.random() * max);
            for(int i = 0 ; i < max ; i++) { // linha
                for(int j = 0 ; j < max ; j++) { // coluna
                    if(i == randomLine && j == randomColumn) {
                        switch (dir) {
                            case UP:
                                if (i - word.length() >= 0 ) {
                                    for (int k = 0; k<word.length(); k++ ) {
                                        grid[i-k][j] = word.charAt(k);
                                    }
                                } else {
                                    dir = Direction.DOWN;
                                }
                                break;
                            case DOWN:
                                if (i + word.length() <= max ) {
                                    for (int k = 0; k<word.length(); k++ ) {
                                        grid[i+k][j] = word.charAt(k);
                                    }
                                } else 
                                    dir = Direction.LEFT;
                                break;
                            case LEFT:
                                if (j - word.length() >= 0 ) {
                                    for (int k = 0; k<word.length(); k++ ) {
                                        grid[i][j-k] = word.charAt(k);
                                    }
                                } else 
                                    dir = Direction.RIGHT;
                                break;
                            case RIGHT:
                                if (j + word.length() <= max ) {
                                    for (int k = 0; k<word.length(); k++ ) {
                                        grid[i][j+k] = word.charAt(k);
                                    }
                                } else 
                                    dir = Direction.UPLEFT;
                                break;
                            case UPLEFT:
                                if (j - word.length() >= 0 && i - word.length() >= 0 ) {
                                    for (int k = 0; k<word.length(); k++ ) {
                                        grid[i-k][j-k] = word.charAt(k);
                                    }
                                } else 
                                    dir = Direction.UPRIGHT;
                                break;
                            case UPRIGHT:
                                if (j + word.length() <= max && i - word.length() >= 0 ) {
                                    for (int k = 0; k<word.length(); k++ ) {
                                        grid[i-k][j+k] = word.charAt(k);
                                    }
                                } else 
                                    dir = Direction.DOWNLEFT;
                                break;
                            case DOWNLEFT:
                                if (j - word.length() >= 0 && i + word.length() <= max ) {
                                    for (int k = 0; k<word.length(); k++ ) {
                                        grid[i+k][j-k] = word.charAt(k);
                                    }
                                } else 
                                    dir = Direction.DOWNRIGHT;
                                break;
                            case DOWNRIGHT:
                                if (j + word.length() <= max && i + word.length() <= max ) {
                                    for (int k = 0; k<word.length(); k++ ) {
                                        grid[i+k][j+k] = word.charAt(k);
                                    }
                                } else 
                                    dir = Direction.UP;
                                break;
                            default:
                                break;                                   
                        }
                    }
                } 
            } 
        }

        for (int i = 0; i < max; i++) { // For each line in the matrix
            for (int j = 0; j < max; j++) { // For each column in the matrix
                System.out.print(grid[i][j]); // Print the element
            }
            System.out.println(); // Print a new line
        }

        try { 
            FileWriter writer = new FileWriter(file2);
            for(int i = 0 ; i < max ; i++) {
                for(int j = 0 ; j < max ; j++) { // Como é que se muda de linha num writer
                    writer.write(grid[i][j]);
                }
                writer.write(System.lineSeparator());
            }
            for(String str : wordlst) {
                int randomSep = (int) ((Math.random() * (sep.size()-1)));
                writer.write(str + sep.get(randomSep)); 
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }  
    }

    // check if string is int
    public static boolean isNumeric(String strNum) {
        try {
            Integer.parseInt(strNum);
        } catch (Exception e) {
            System.out.println("ERROR: 3rd argument must be an integer");
            return false;
        }
        return true;
    }
    
}