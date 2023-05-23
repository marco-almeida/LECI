package lab11.Ex1;

public class MagazineMain {
    public static void main(String[] args) {
        SortStrategy s1 = new StratSelect();
        SortStrategy s2 = new StratBubble();
        SortStrategy s3 = new StratInsertion();

        Magazine m1 = new Magazine(s1);
        m1.sort();

        m1.setStrategy(s2);
        m1.sort();

        m1.setStrategy(s3);
        m1.sort();

    
    }
}
