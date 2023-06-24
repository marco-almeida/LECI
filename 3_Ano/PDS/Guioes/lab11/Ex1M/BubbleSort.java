package lab11.Ex1M;

public class BubbleSort implements Strategy {

    @Override
    public void sortBy(String attribute) {
        System.out.printf("Sorting by %s using Bubble Sort\n", attribute);
    }
}
