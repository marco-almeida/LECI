package lab09.Ex01;

import java.util.Iterator;
import java.util.ListIterator;

public class Ex01 {
    public static void main(String[] args) {
        // Ficheiro de testes adaptado
        VectorGeneric<Integer> vector = new VectorGeneric<>();

        for (int i = 0; i < 10; i++)
            vector.addElem(i);

        System.out.println("\nITERATOR TEST\n");
        Iterator<Integer> normalIterator = vector.Iterator();

        while (normalIterator.hasNext())
            System.out.println("Element: " + normalIterator.next());

        System.out.println("\nLIST ITERATOR TEST\n");
        ListIterator<Integer> normalListIterator = vector.listIterator();

        System.out.println("\nFORWARD ITERATION");
        while (normalListIterator.hasNext())
            System.out.println("Next index: " + normalListIterator.nextIndex() + " Next element: " + normalListIterator.next());

        System.out.println("\nBACKWARDS ITERATION");
        while (normalListIterator.hasPrevious())
            System.out.println("Previous index: " + normalListIterator.previousIndex() + " Previous element: " + normalListIterator.previous());

        System.out.println("\nLIST ITERATOR W/ SPECIFIED INDEX TEST\n");
        ListIterator<Integer> indexedListIterator = vector.listIterator(4);

        System.out.println("\nFORWARD ITERATION");
        while (indexedListIterator.hasNext())
            System.out.println("Next index: " + indexedListIterator.nextIndex() + " Next element: " + indexedListIterator.next());

        System.out.println("\nBACKWARDS ITERATION");
        while (indexedListIterator.hasPrevious())
            System.out.println("Previous index: " + indexedListIterator.previousIndex() + " Previous element: " + indexedListIterator.previous());

        System.out.println("\nLIST ITERATOR W/ SPECIFIED INDEX and NORMAL INTERATOR TEST\n");
        indexedListIterator = vector.listIterator(4);
        normalIterator = vector.Iterator();
        Iterator<Integer> anotherIterator = vector.Iterator();
        anotherIterator.next();
        anotherIterator.next();

        while (indexedListIterator.hasNext()) {
            System.out.println("List --> Next index: " + indexedListIterator.nextIndex() + " Next element: " + indexedListIterator.next());
            System.out.println("Normal --> Element: " + normalIterator.next());
        }
    }
}
