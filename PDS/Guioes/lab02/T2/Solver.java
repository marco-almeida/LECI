
import java.util.ArrayList;
import java.util.List;


public class Solver {
    
    private List<ArrayList<String>> puzzle;
    private List<String> words;

    private List<ArrayList<String>> finalPuzzle = new ArrayList<ArrayList<String>>();
    private List<Word> wordsResults = new ArrayList<Word>();


    public Solver(List<ArrayList<String>> puzzle, List<String> words) {
        this.puzzle = puzzle;
        this.words = words;
    }

    public void solve() {

        for(int w = 0; w < words.size(); w++){
            int x = FindDiagonal(words.get(w));
            if(x == 0){
                x = FindHorizontal(words.get(w));
                if(x == 0){
                    x = FindVertical(words.get(w));
                }
            }   
        }  
    }

    private int FindDiagonal(String word) {
        int find = 0;
        
        for(int i = 0; i < puzzle.size(); i++){
            for(int j = 0; j < puzzle.get(i).size(); j++){
                if(puzzle.get(i).get(j).equals(word.substring(0, 1).toUpperCase())){
                    if(i + word.length() <= puzzle.size() && j + word.length() <= puzzle.get(i).size()){
                        for(int k = 0; k < word.length(); k++){
                            if(puzzle.get(i + k).get(j + k).equals(word.substring(k, k + 1).toUpperCase())){
                                find = 1;
                            }else{
                                find = 0;
                                break;
                            }
                        }
                        if(find == 1){
                            wordsResults.add(new Word(word, word.length(), i+1, j+1, Direction.DownRight));
                        }
                    }else if (i + word.length() <= puzzle.size() && j - word.length() >= 0){
                        for(int k = 0; k < word.length(); k++){
                            if(puzzle.get(i + k).get(j - k).equals(word.substring(k, k + 1).toUpperCase())){
                                find = 1;
                            }else{
                                find = 0;
                                break;
                            }
                        }
                        if(find == 1){
                            wordsResults.add(new Word(word, word.length(), i+1, j+1, Direction.DownLeft));
                        }
                    }else if (i - word.length() >= 0 && j + word.length() <= puzzle.get(i).size()){
                        for(int k = 0; k < word.length(); k++){
                            if(puzzle.get(i - k).get(j + k).equals(word.substring(k, k + 1).toUpperCase())){
                                find = 1;
                            }else{
                                find = 0;
                                break;
                            }
                        }
                        if(find == 1){
                            wordsResults.add(new Word(word, word.length(), i+1, j+1, Direction.UpRight));
                        }
                    }else if (i - word.length() >= 0 && j - word.length() >= 0){
                        for(int k = 0; k < word.length(); k++){
                            if(puzzle.get(i - k).get(j - k).equals(word.substring(k, k + 1).toUpperCase())){
                                find = 1;
                            }else{
                                find = 0;
                                break;
                            }
                        }
                        if(find == 1){
                            wordsResults.add(new Word(word, word.length(), i+1, j+1, Direction.UpLeft));
                        }
                    }
                 
                }
            }
        }

        return find;
    }

    private int FindVertical(String word) {
        int find = 0;

        for(int i = 0; i < puzzle.size(); i++){
            for(int j = 0; j < puzzle.get(i).size(); j++){
                if(puzzle.get(i).get(j).equals(word.substring(0, 1).toUpperCase())){
                    if(i + word.length() <= puzzle.size()){
                        for(int k = 0; k < word.length(); k++){
                            if(puzzle.get(i + k).get(j).equals(word.substring(k, k + 1).toUpperCase())){
                                find = 1;
                            }else{
                                find = 0;
                                break;
                            }
                        }
                        if(find == 1){
                            wordsResults.add(new Word(word, word.length(), i+1, j+1, Direction.Down));
                        }
                    }else if (i - word.length() >= 0){
                        for(int k = 0; k < word.length(); k++){
                            if(puzzle.get(i - k).get(j).equals(word.substring(k, k + 1).toUpperCase())){
                                find = 1;
                            }else{
                                find = 0;
                                break;
                            }
                        }
                        if(find == 1){
                            wordsResults.add(new Word(word, word.length(), i+1, j+1, Direction.Up));
                        }
                    }
                }
            }
        }
        return 0;
    }

    public int FindHorizontal(String word){
        
        int find = 0;

        for(int i = 0; i < puzzle.size(); i++){
            for(int j = 0; j < puzzle.get(i).size(); j++){
                if(puzzle.get(i).get(j).equals(word.substring(0, 1).toUpperCase())){
                    if(j + word.length() <= puzzle.get(i).size()){
                        for(int k = 0; k < word.length(); k++){
                            if(puzzle.get(i).get(j + k).equals(word.substring(k, k + 1).toUpperCase())){
                                find = 1;
                            }else{
                                find = 0;
                                break;
                            }
                        }
                        if(find == 1){
                            wordsResults.add(new Word(word, word.length(), i+1, j+1, Direction.Right));
                        }
                    }else if(j - word.length() >= 0){
                        for(int k = 0; k < word.length(); k++){
                            if(puzzle.get(i).get(j - k).equals(word.substring(k, k + 1).toUpperCase())){
                                find = 1;
                            }else{
                                find = 0;
                                break;
                            }
                        }
                        if(find == 1){
                            wordsResults.add(new Word(word, word.length(), i+1, j+1, Direction.Left));
                        }
                    }
                }
            }
        }

        return find;
    }


    public void makefinalPuzzle(){

        for(int i = 0; i < puzzle.size(); i++){
            ArrayList<String> temp = new ArrayList<String>();
            for(int j = 0; j < puzzle.get(i).size(); j++){
                temp.add(".");
            }
            finalPuzzle.add(temp);
        }

        for(int i = 0; i < wordsResults.size(); i++){
            int x = wordsResults.get(i).getX();
            int y = wordsResults.get(i).getY();
            String word = wordsResults.get(i).getWord();
            Direction direction = wordsResults.get(i).getDirection();

            if(direction == Direction.Right){
                for(int j = 0; j < word.length(); j++){
                    finalPuzzle.get(x - 1).set(y - 1 + j, word.substring(j, j + 1).toUpperCase());
                }
            }else if(direction == Direction.Left){
                for(int j = 0; j < word.length(); j++){
                    finalPuzzle.get(x - 1).set(y - 1 - j, word.substring(j, j + 1).toUpperCase());
                }
            }else if(direction == Direction.Down){
                for(int j = 0; j < word.length(); j++){
                    finalPuzzle.get(x - 1 + j).set(y - 1, word.substring(j, j + 1).toUpperCase());
                }
            }else if(direction == Direction.Up){
                for(int j = 0; j < word.length(); j++){
                    finalPuzzle.get(x - 1 - j).set(y - 1, word.substring(j, j + 1).toUpperCase());
                }
            }else if(direction == Direction.DownRight){
                for(int j = 0; j < word.length(); j++){
                    finalPuzzle.get(x - 1 + j).set(y - 1 + j, word.substring(j, j + 1).toUpperCase());
                }
            }else if(direction == Direction.DownLeft){
                for(int j = 0; j < word.length(); j++){
                    finalPuzzle.get(x - 1 + j).set(y - 1 - j, word.substring(j, j + 1).toUpperCase());
                }
            }else if(direction == Direction.UpRight){
                for(int j = 0; j < word.length(); j++){
                    finalPuzzle.get(x - 1 - j).set(y - 1 + j, word.substring(j, j + 1).toUpperCase());
                }
            }else if(direction == Direction.UpLeft){
                for(int j = 0; j < word.length(); j++){
                    finalPuzzle.get(x - 1 - j).set(y - 1 - j, word.substring(j, j + 1).toUpperCase());
                }
            }
        }

        
    }


    public String toStringPuzzle(){
        String output = "";
        for(int i = 0; i < puzzle.size(); i++){
            for(int j = 0; j < puzzle.get(i).size(); j++){
                output += puzzle.get(i).get(j) + " ";
            }
            output += "\n";
        }
        return output;
    }

    public String toStringWords(){
        String output = "";
        for(int i = 0; i < words.size(); i++){
            output += words.get(i) + " ";
        }
        return output;
    }

    public String toStringResults(){
        String output = String.format("%-14s %-7s %-7s %-10s\n", "Word", "Length", "Cords", "Direction");
        
        for(int i = 0; i < wordsResults.size(); i++){
            output += wordsResults.get(i).toString() + "\n";
        }
        return output;
    } 

    public String toStringFinalPuzzle(){
        String output = "";
        for(int i = 0; i < finalPuzzle.size(); i++){
            for(int j = 0; j < finalPuzzle.get(i).size(); j++){
                output += finalPuzzle.get(i).get(j) + " ";
            }
            output += "\n";
        }
        return output;
    }



}
