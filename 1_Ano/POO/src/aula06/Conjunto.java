package aula06;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Conjunto {
    private int vetor[];
    private int size;

    public Conjunto() {
        this.vetor = new int[10];
        this.size = 0;
    }

    private void increaseSpace() {
        vetor = Arrays.copyOf(vetor, vetor.length + 10);
    }

    /**
     * Inserts element n in data structure
     * 
     * @param n
     */
    public void insert(int n) {
        if (!contains(n)) {
            // if array is full
            if (size == vetor.length) {
                increaseSpace();
            }
            vetor[size] = n;
            size++;
        }
    }

    public boolean contains(int n) {
        for (int i = 0; i < size; i++) {
            if (vetor[i] == n) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes element n from data structure
     * 
     * @param n
     */
    public void remove(int n) {
        if (contains(n)) {
            int index = IntStream.range(0, size).filter(i -> vetor[i] == n).findFirst().orElse(-1);
            for (int i = index; i < size - 1; i++) {
                vetor[i] = vetor[i + 1];
            }
            size--;
        }
    }

    /**
     * resets data structure
     */
    public void empty() {
        this.vetor = new int[10];
        this.size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(vetor[i] + " ");
        }
        return sb.toString();
    }

    public int size() {
        return size;
    }

    /**
     * Combines this object with another object
     * 
     * @param add
     * @return New object made out of both objects
     */
    public Conjunto combine(Conjunto add) {
        Conjunto novoConjunto = new Conjunto();
        for (int i = 0; i < size; i++) {
            novoConjunto.insert(vetor[i]);
        }
        for (int i = 0; i < add.size(); i++) {
            novoConjunto.insert(add.vetor[i]);
        }
        return novoConjunto;
    }

    /**
     * Subtracts common elements from original object
     * 
     * @param dif
     * @return New subtracted object
     */
    public Conjunto subtract(Conjunto dif) {
        Conjunto novoConjunto = new Conjunto();
        for (int i = 0; i < size; i++) {
            if (!dif.contains(vetor[i])) {
                novoConjunto.insert(vetor[i]);
            }
        }
        return novoConjunto;
    }

    /**
     * Intersects this object with another in order to return all common elements
     * 
     * @param inter
     * @return New object with all common elements
     */
    public Conjunto intersect(Conjunto inter) {
        Conjunto novoConjunto = new Conjunto();
        for (int i = 0; i < inter.size(); i++) {
            if (contains(inter.vetor[i])) {
                novoConjunto.insert(inter.vetor[i]);
            }
        }
        return novoConjunto;
    }
}
