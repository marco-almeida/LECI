package lab01;

public class Solution {
    private int[] startingCoordinates;
    private String word;
    private String direction;

    public Solution(int[] startingCoordinates, String word, String direction) {
        this.startingCoordinates = startingCoordinates;
        this.word = word;
        this.direction = direction;
    }

    public int[] getStartingCoordinates() {
        return startingCoordinates;
    }

    public String getWord() {
        return word;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-7d %d,%-7d%s\n", word, word.length(), startingCoordinates[0],
                startingCoordinates[1], direction);
    }

}
