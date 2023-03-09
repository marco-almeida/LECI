import java.util.ArrayList;
import java.util.List;

public class CreatePuzzle {

    private List<String> words;
    private int size;
    private List<ArrayList<String>> puzzle = new ArrayList<ArrayList<String>>();
    private List<String> alphabet = new ArrayList<String>();

    public CreatePuzzle(List<String> words, int size) {
        this.words = words;
        this.size = size;
    }

    public void makeAlphabetList() {
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet.add(String.valueOf(c));
        }
    }

    public void makeEmptyPuzzle() {
        for (int i = 0; i < size; i++) {
            ArrayList<String> subPuzzle = new ArrayList<String>();
            for (int j = 0; j < size; j++) {
                subPuzzle.add(".");
            }
            puzzle.add(subPuzzle);
        }
    }

    public int validation(Direction dir, int x, int y, int wordSize) {

        if (dir == Direction.Up) {
            if (y - wordSize >= 0) {
                for (int i = 0; i < wordSize; i++) {
                    if (puzzle.get(y - i).get(x) != ".") {
                        return 0;
                    }
                }
            }else{
                return 0;
            }

        } else if (dir == Direction.Down) {
            if (y + wordSize < size) {
                for (int i = 0; i < wordSize; i++) {
                    if (puzzle.get(y + i).get(x) != ".") {
                        return 0;
                    }
                }
            } else {
                return 0;
            }
        } else if (dir == Direction.Left) {
            if (x - wordSize >= 0) {
                for (int i = 0; i < wordSize; i++) {
                    if (puzzle.get(y).get(x - i) != ".") {
                        return 0;
                    }
                }
            } else {
                return 0;
            }
        } else if (dir == Direction.Right) {
            if (x + wordSize < size) {
                for (int i = 0; i < wordSize; i++) {
                    if (puzzle.get(y).get(x + i) != ".") {
                        return 0;
                    }
                }
            } else {
                return 0;
            }
        } else if (dir == Direction.UpLeft) {
            if (x - wordSize >= 0 && y - wordSize >= 0) {
                for (int i = 0; i < wordSize; i++) {
                    if (puzzle.get(y - i).get(x - i) != ".") {
                        return 0;
                    }
                }
            } else {
                return 0;
            }
        } else if (dir == Direction.UpRight) {
            if (x + wordSize < size && y - wordSize >= 0) {
                for (int i = 0; i < wordSize; i++) {
                    if (puzzle.get(y - i).get(x + i) != ".") {
                        return 0;
                    }
                }
            } else {
                return 0;
            }
        } else if (dir == Direction.DownLeft) {
            if (x - wordSize >= 0 && y + wordSize < size) {
                for (int i = 0; i < wordSize; i++) {
                    if (puzzle.get(y + i).get(x - i) != ".") {
                        return 0;
                    }
                }
            } else {
                return 0;
            }
        } else if (dir == Direction.DownRight) {
            if (x + wordSize < size && y + wordSize < size) {
                for (int i = 0; i < wordSize; i++) {
                    if (puzzle.get(y + i).get(x + i) != ".") {
                        return 0;
                    }
                }
            } else {
                return 0;
            }
        }

        return 1;
    }

    public List<ArrayList<String>> puzzle() {
        makeAlphabetList();
        makeEmptyPuzzle();

        // adicionar as palavras ao puzzle
        for (String word : words) {
            int wordSize = word.length();
            int x = (int) (Math.random() * size);
            int y = (int) (Math.random() * size);
            Direction dir = Direction.values()[(int) (Math.random() * Direction.values().length)];

        

            while (validation(dir, x, y, wordSize) == 0) {
                x = (int) (Math.random() * size);
                y = (int) (Math.random() * size);
                dir = Direction.values()[(int) (Math.random() * Direction.values().length)];
            }

            if (dir == Direction.Up) {
                for (int i = 0; i < wordSize; i++) {
                    puzzle.get(y - i).set(x, String.valueOf(word.charAt(i)).toUpperCase());
                }
            } else if (dir == Direction.Down) {
                for (int i = 0; i < wordSize; i++) {
                    puzzle.get(y + i).set(x, String.valueOf(word.charAt(i)).toUpperCase());
                }
            } else if (dir == Direction.Left) {
                for (int i = 0; i < wordSize; i++) {
                    puzzle.get(y).set(x - i, String.valueOf(word.charAt(i)).toUpperCase());
                }
            } else if (dir == Direction.Right) {
                for (int i = 0; i < wordSize; i++) {
                    puzzle.get(y).set(x + i, String.valueOf(word.charAt(i)).toUpperCase());
                }
            } else if (dir == Direction.UpLeft) {
                for (int i = 0; i < wordSize; i++) {
                    puzzle.get(y - i).set(x - i, String.valueOf(word.charAt(i)).toUpperCase());
                }
            } else if (dir == Direction.UpRight) {
                for (int i = 0; i < wordSize; i++) {
                    puzzle.get(y - i).set(x + i, String.valueOf(word.charAt(i)).toUpperCase());
                }
            } else if (dir == Direction.DownLeft) {
                for (int i = 0; i < wordSize; i++) {
                    puzzle.get(y + i).set(x - i, String.valueOf(word.charAt(i)).toUpperCase());
                }
            } else if (dir == Direction.DownRight) {
                for (int i = 0; i < wordSize; i++) {
                    puzzle.get(y + i).set(x + i, String.valueOf(word.charAt(i)).toUpperCase());
                }
            }
            

            
        }

        // preencher o puzzle com letras aleatorias
        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(i).size(); j++) {
                if (puzzle.get(i).get(j).equals(".")) {
                    puzzle.get(i).set(j, String.valueOf(alphabet.get((int) (Math.random() * alphabet.size()))));
                }
            }
        }
        
        return puzzle;
    }

}
