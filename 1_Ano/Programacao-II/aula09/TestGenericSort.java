// This program should use the generic mergeSort method in p2utils.Sorting
// to sort its string arguments.

//...

import static p2utils.Sorting.*;

import java.util.Arrays;

public class TestGenericSort {
    public static void main(String[] args) {
        System.out.println("Before:");
        System.out.println(Arrays.toString(args));
        mergeSort(args, 0, args.length);
        System.out.println("After:");
        System.out.println(Arrays.toString(args));
        Arrays.sort(args);
        System.out.println("How it should be:" + "\n" + Arrays.toString(args));
    }
}