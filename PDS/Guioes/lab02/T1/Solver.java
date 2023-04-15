import java.util.*;

public class Solver {
    public HashMap<String, HashMap<Direction,ArrayList<Integer>>> wordSolver(ArrayList<String> wordLst, Character[][] wSolver, int nLines, int nColumns, Direction dir ) {
        HashMap<String, HashMap<Direction, ArrayList<Integer>>> solutions = new HashMap<String, HashMap<Direction, ArrayList<Integer>>>();
        for(String str : wordLst) { // Run through the word list
            str = str.toUpperCase(); // Convert the word to uppercase
            int wordLenght = str.length(); // Get the lenght of the word
            for(int i = 0 ; i < nLines ; i++) { // Run through the lines in the wSolver
                for(int j = 0 ; j < nColumns ; j++) { // Run through the columns in the wSolver
                    int comp = Character.compare(wSolver[i][j], str.charAt(0)); // Compare the first letter of the word with the letter in the wSolver
                    if(comp == 0) { // If the letters are the same
                        if(dir == Direction.LEFT) { // If the direction is left
                            if(j - wordLenght >= 0) { // If the word fits in the wSolver
                                String s = "";

                                for(int k = 0 ; k < wordLenght ; k++) // Create a string with the word
                                    s = s + wSolver[i][j-k];
                                if(s.equals(str)) { // If the string is the same as the word
                                    HashMap<Direction, ArrayList<Integer>> temp = new HashMap<Direction, ArrayList<Integer>>();
                                    ArrayList<Integer> temp2 = new ArrayList<Integer>();
                                    
                                    temp2.add(i + 1); // Add the line and +1 because i starts at 0
                                    temp2.add(j + 1); // Add the column and +1 because j starts at 0
                                    temp2.add(wordLenght); // Add the lenght of the word
                                    temp.put(dir, temp2);
                                    str = str.toLowerCase(); 
                                    solutions.put(str, temp);
                                    dir = Direction.LEFT;
                                } else {
                                    dir = Direction.RIGHT;
                                    j--;
                                    continue; 
                                }
                                
                            } else {
                                dir = Direction.RIGHT;
                                j--;
                                continue;
                            }
                        } else if(dir == Direction.RIGHT) { // If the direction is right

                            if(j + wordLenght <= nColumns) { // If the word fits in the wSolver

                                String s = "";

                                for(int k = 0 ; k < wordLenght ; k++) // Create a string with the word
                                    s = s + wSolver[i][j+k];

                                if(s.equals(str)) { // If the string is the same as the word
                                    HashMap<Direction, ArrayList<Integer>> temp = new HashMap<Direction, ArrayList<Integer>>();
                                    ArrayList<Integer> temp2 = new ArrayList<Integer>();

                                    temp2.add(i + 1); // Add the line and +1 because i starts at 0
                                    temp2.add(j + 1); // Add the column and +1 because j starts at 0
                                    temp2.add(wordLenght); // Add the lenght of the word
                                    temp.put(dir, temp2);
                                    str = str.toLowerCase();
                                    solutions.put(str, temp);
                                    dir = Direction.LEFT;
                                } else {
                                    dir = Direction.UP;
                                    j--;
                                    continue;
                                }
                            } else {
                                dir = Direction.UP;
                                j--;
                                continue;
                            }
                        } else if(dir == Direction.UP) { // If the direction is up

                            if(i - wordLenght >= 0) { // If the word fits in the wSolver

                                String s = "";

                                for(int k = 0 ; k < wordLenght ; k++)  // Create a string with the word
                                    s = s + wSolver[i-k][j];

                                if(s.equals(str)) { // If the string is the same as the word
                                    HashMap<Direction, ArrayList<Integer>> temp = new HashMap<Direction, ArrayList<Integer>>();
                                    ArrayList<Integer> temp2 = new ArrayList<Integer>();

                                    temp2.add(i + 1); // Add the line and +1 because i starts at 0
                                    temp2.add(j + 1); // Add the column and +1 because j starts at 0
                                    temp2.add(wordLenght); // Add the lenght of the word
                                    temp.put(dir, temp2);
                                    str = str.toLowerCase();
                                    solutions.put(str, temp);
                                    dir = Direction.LEFT;
                                } else {
                                    dir = Direction.DOWN;
                                    j--;
                                    continue;
                                }
                            } else {
                                dir = Direction.DOWN;
                                j--;
                                continue;
                            }
                        } else if(dir == Direction.DOWN) { // If the direction is down

                            if(i + wordLenght <= nLines) { // If the word fits in the wSolver

                                String s = "";

                                for(int k = 0 ; k < wordLenght ; k++) // Create a string with the word
                                    s = s + wSolver[i+k][j];


                                if(s.equals(str)) { // If the string is the same as the word
                                    HashMap<Direction, ArrayList<Integer>> temp = new HashMap<Direction, ArrayList<Integer>>();
                                    ArrayList<Integer> temp2 = new ArrayList<Integer>();
                                    
                                    temp2.add(i + 1); // Add the line and +1 because i starts at 0
                                    temp2.add(j + 1); // Add the column and +1 because j starts at 0
                                    temp2.add(wordLenght); // Add the lenght of the word
                                    temp.put(dir, temp2);
                                    str = str.toLowerCase();
                                    solutions.put(str, temp);
                                    dir = Direction.LEFT;
                                } else {
                                    dir = Direction.UPLEFT;
                                    j--;
                                    continue;
                                }
                            } else {
                                dir = Direction.UPLEFT;
                                j--;
                                continue;
                            }
                        } else if (dir == Direction.UPLEFT) { // If the direction is diagonal up and to the left
                            if(i - wordLenght >= 0 && j - wordLenght >= 0) { // If the word fits in the wSolver
                                String s = "";

                                for(int k = 0 ; k < wordLenght ; k++) // Create a string with the word
                                    s = s + wSolver[i-k][j-k];


                                if(s.equals(str)) { // If the string is the same as the word
                                    HashMap<Direction, ArrayList<Integer>> temp = new HashMap<Direction, ArrayList<Integer>>();
                                    ArrayList<Integer> temp2 = new ArrayList<Integer>();

                                    temp2.add(i + 1); // Add the line and +1 because i starts at 0
                                    temp2.add(j + 1); // Add the column and +1 because j starts at 0
                                    temp2.add(wordLenght);
                                    temp.put(dir, temp2);
                                    str = str.toLowerCase();
                                    solutions.put(str, temp);
                                    dir = Direction.LEFT;
                                } else {
                                    dir = Direction.UPRIGHT;
                                    j--;
                                    continue;
                                }
                            } else {
                                dir = Direction.UPRIGHT;
                                j--;
                                continue;
                            }
                        } else if (dir == Direction.UPRIGHT) { // If the direction is diagonal up and to the right
                            if(i - wordLenght >= 0 && j + wordLenght <= nColumns) { // If the word fits in the wSolver
                                String s = "";

                                for(int k = 0 ; k < wordLenght ; k++) // Create a string with the word
                                    s = s + wSolver[i-k][j+k];    

                                if(s.equals(str)) { // If the string is the same as the word
                                    HashMap<Direction, ArrayList<Integer>> temp = new HashMap<Direction, ArrayList<Integer>>();
                                    ArrayList<Integer> temp2 = new ArrayList<Integer>();

                                    temp2.add(i + 1); // Add the line and +1 because i starts at 0
                                    temp2.add(j + 1); // Add the column and +1 because j starts at 0
                                    temp2.add(wordLenght);
                                    temp.put(dir, temp2);
                                    str = str.toLowerCase();
                                    solutions.put(str, temp);
                                    dir = Direction.LEFT;
                                } else {
                                    dir = Direction.DOWNLEFT;
                                    j--;
                                    continue;
                                }
                            } else {
                                dir = Direction.DOWNLEFT;
                                j--;
                                continue;
                            }
                        } else if (dir == Direction.DOWNLEFT) { // If the direction is diagonal down and to the left

                            if(i + wordLenght <= nLines && j - wordLenght >= 0) { // If the word fits in the wSolver
                                String s = "";

                                for(int k = 0 ; k < str.length() ; k++) // Create a string with the word
                                    s = s + wSolver[i+k][j-k];

                                if(s.equals(str)) { // If the string is the same as the word
                                    HashMap<Direction, ArrayList<Integer>> temp = new HashMap<Direction, ArrayList<Integer>>();
                                    ArrayList<Integer> temp2 = new ArrayList<Integer>();

                                    temp2.add(i + 1); // Add the line and +1 because i starts at 0
                                    temp2.add(j + 1); // Add the column and +1 because j starts at 0
                                    temp2.add(wordLenght);
                                    temp.put(dir, temp2);
                                    str = str.toLowerCase();
                                    solutions.put(str, temp);
                                    dir = Direction.LEFT;
                                } else {
                                    dir = Direction.DOWNRIGHT;
                                    j--;
                                    continue;
                                }
                            } else {
                                dir = Direction.DOWNRIGHT;
                                j--;
                                continue;
                            }
                        } else if (dir == Direction.DOWNRIGHT) { // If the direction is diagonal down and to the right

                            if(i + wordLenght <= nLines && j + wordLenght <= nColumns) { // If the word fits in the wSolver
                                String s = "";

                                for(int k = 0 ; k < wordLenght ; k++)  // Create a string with the word
                                    s = s + wSolver[i+k][j+k];

                                if(s.equals(str)) { // If the string is the same as the word
                                    HashMap<Direction, ArrayList<Integer>> temp = new HashMap<Direction, ArrayList<Integer>>();
                                    ArrayList<Integer> temp2 = new ArrayList<Integer>();

                                    temp2.add(i + 1); // Add the line and +1 because i starts at 0
                                    temp2.add(j + 1); // Add the column and +1 because j starts at 0
                                    temp2.add(wordLenght);
                                    temp.put(dir, temp2);
                                    str = str.toLowerCase();
                                    solutions.put(str, temp);
                                    dir = Direction.LEFT;
                                } else {
                                    dir = Direction.LEFT;
                                    continue; 
                                }
                            } else {
                                dir = Direction.LEFT;
                                continue;
                            }
                        }
                    }
                }
                
            }
            
        }
        return solutions;
    }

    public void printSolutions(HashMap<String,HashMap<Direction,ArrayList<Integer>>> solutions, int nLines, int nColumns) {
        for(String str : solutions.keySet()) {
            for(Direction dir : solutions.get(str).keySet()) {
                int line = solutions.get(str).get(dir).get(0);
                int column = solutions.get(str).get(dir).get(1);
                int wordLenght = solutions.get(str).get(dir).get(2);
                System.out.printf("%-15s %-5d %4d,%-5d %-5s \n", str, wordLenght, line, column, dir);
            }
        }
        Character[][] wSolverSolved = new Character[nLines][nColumns];
        for (int i = 0; i < nLines; i++) {
            for (int j = 0; j < nColumns; j++) {
                wSolverSolved[i][j] = '.';
            }
        }
        for(String str : solutions.keySet()) {
            for(Direction dir : solutions.get(str).keySet()) {
                int line = solutions.get(str).get(dir).get(0) - 1;
                int column = solutions.get(str).get(dir).get(1) - 1;
                int wordLenght = solutions.get(str).get(dir).get(2);
                str = str.toUpperCase();
                for(int i = 0 ; i < nLines ; i++) { 
                    for(int j = 0 ; j < nColumns ; j++) {
                        if(i == line && j == column) { // If the position is the same as the first letter of the word
                            switch (dir) { 
                                case LEFT: 
                                    for(int k = 0 ; k < wordLenght ; k++) {
                                        wSolverSolved[i][j-k] = str.charAt(k);
                                    }
                                    break;
                                case UPLEFT: 
                                    for(int k = 0 ; k < wordLenght ; k++) {
                                        wSolverSolved[i-k][j-k] = str.charAt(k);
                                    }
                                    break;
                                case UP: 
                                    for(int k = 0 ; k < wordLenght ; k++) {
                                        wSolverSolved[i-k][j] = str.charAt(k);
                                    }
                                    break;
                                case UPRIGHT: 
                                    for(int k = 0 ; k < wordLenght ; k++) {
                                        wSolverSolved[i-k][j+k] = str.charAt(k);
                                    }
                                    break;
                                case RIGHT: 
                                    for(int k = 0 ; k < wordLenght ; k++) {
                                        wSolverSolved[i][j+k] = str.charAt(k);
                                    }
                                    break;
                                case DOWNRIGHT: 
                                    for(int k = 0 ; k < wordLenght ; k++) {
                                        wSolverSolved[i+k][j+k] = str.charAt(k);
                                    }
                                    break;
                                case DOWN: 
                                    for(int k = 0 ; k < wordLenght ; k++) {
                                        wSolverSolved[i+k][j] = str.charAt(k);
                                    }
                                    break;
                                case DOWNLEFT: 
                                    for(int k = 0 ; k < wordLenght ; k++) {
                                        wSolverSolved[i+k][j-k] = str.charAt(k);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < nLines; i++) { // For each line in the matrix
            for (int j = 0; j < nColumns; j++) { // For each column in the matrix
                System.out.print(wSolverSolved[i][j] + " "); // Print the letter
            }
            System.out.println(); // Print a new line
        }
    }
}
