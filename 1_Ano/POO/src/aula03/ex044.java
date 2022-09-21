package aula03;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ex044 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Double> lista = new ArrayList<>();

        do {
            System.out.print("Inserir número real: ");
            lista.add(sc.nextDouble());
            if (lista.size() == 1) {
                System.out.print("Inserir número real: ");
                lista.add(sc.nextDouble());
            }
        } while ((!lista.get(0).equals(lista.get(lista.size() - 1))));

        sc.close();
        System.out.printf("Valor máximo: %4.2f%nValor mínimo: %4.2f%n Média: %4.2f%n Total de números: %d",
                Collections.max(lista), Collections.min(lista),
                lista.stream()
                        .mapToDouble(a -> a)
                        .sum() / lista.size(),
                lista.size());
    }
}
