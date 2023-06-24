package lab11.Ex1M;

public class MergeSort implements Strategy {

    @Override
    public void sortBy(String attribute) {
        System.out.printf("Sorting by %s using Merge Sort\n", attribute);
    }
}
