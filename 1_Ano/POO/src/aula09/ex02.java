package aula09;

import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

public class ex02 {

    public static void main(String[] args) {
        int[] DIM = { 1000, 5000, 10000, 20000, 40000, 100000 };
        Collection<Integer> col = new ArrayList<>();
        double realResults[][] = new double[6][3];
        System.out.printf("Collection%12s%9s%9s%9s%9s%9s\n", 1000, 5000, 10000, 20000, 40000, 100000);
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 1:
                    col = new LinkedList<>();
                    break;
                case 2:
                    col = new HashSet<>();
                    break;
                case 3:
                    col = new TreeSet<>();
                    break;
            }
            for (int j = 0; j < 6; j++) {
                double results[] = checkPerformance(col, DIM[j]);
                realResults[j][0] = results[0];
                realResults[j][1] = results[1];
                realResults[j][2] = results[2];
            }
            System.out.println(col.getClass().getSimpleName());
            System.out.printf("add%19.1f%9.1f%9.1f%9.1f%9.1f%9.1f\n", realResults[0][0], realResults[1][0],
                    realResults[2][0], realResults[3][0], realResults[4][0], realResults[5][0]);
            System.out.printf("search%16.1f%9.1f%9.1f%9.1f%9.1f%9.1f\n", realResults[0][1], realResults[1][1],
                    realResults[2][1], realResults[3][1], realResults[4][1], realResults[5][1]);
            System.out.printf("remove%16.1f%9.1f%9.1f%9.1f%9.1f%9.1f\n", realResults[0][2], realResults[1][2],
                    realResults[2][2], realResults[3][2], realResults[4][2], realResults[5][2]);
        }
    }

    private static double[] checkPerformance(Collection<Integer> col, int DIM) {
        double start, stop, add, search, remove; // Add
        start = System.nanoTime(); // clock snapshot before
        for (int i = 0; i < DIM; i++)
            col.add(i);
        stop = System.nanoTime(); // clock snapshot after
        add = (stop - start) / 1e6; // convert to milliseconds
        start = System.nanoTime(); // clock snapshot before
        for (int i = 0; i < DIM; i++) {
            int n = (int) (Math.random() * DIM);
            if (!col.contains(n))
                System.out.println("Not found???" + n);
        }
        stop = System.nanoTime(); // clock snapshot after
        search = (stop - start) / 1e6; // convert nanoseconds to milliseconds
        start = System.nanoTime(); // clock snapshot before
        Iterator<Integer> iterator = col.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        stop = System.nanoTime(); // clock snapshot after
        remove = (stop - start) / 1e6; // convert nanoseconds to milliseconds
        return new double[] { add, search, remove };
    }
}
