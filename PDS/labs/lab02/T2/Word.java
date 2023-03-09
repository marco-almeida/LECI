
public class Word {

    private String word;
    private int length, x, y;
    private Direction direction;

    public Word(String word, int length, int x, int y, Direction direction) {
        this.word= word;
        this.length = length;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public String getWord() {
        return word;
    }

    public int getLength() {
        return length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }
    
    public String toString(){
        return String.format("%-14s %-7d %d,%-7d %-10s", word, length, x, y, direction);
    }

}
