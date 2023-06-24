package lab11.Ex1M;

public class Client {
    public static void main(String[] args) {
        String[] attributes = {"CPU", "Price", "Memory"};
        Context c = new Context(new MergeSort());
        c.sortBy(attributes[0]);
        c.setStrategy(new SelectionSort());
        c.sortBy(attributes[1]);
        c.setStrategy(new BubbleSort());
        c.sortBy(attributes[2]);
    }
}
