package lab11.Ex1M;

public class SelectionSort implements Strategy {

    @Override
    public void sortBy(String attribute) {
        System.out.printf("Sorting by %s using Selection Sort\n", attribute);
    }
}
