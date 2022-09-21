package aula09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import aula05.Date;
import aula06.Pessoa;

public class ex01 {

    public static void main(String[] args) {
        ArrayList<Integer> c1 = new ArrayList<>();
        for (int i = 10; i <= 100; i += 10)
            c1.add(i);
        System.out.println("Size: " + c1.size());
        for (int i = 0; i < c1.size(); i++)
            System.out.println("Elemento: " + c1.get(i));

        ArrayList<String> c2 = new ArrayList<>();
        c2.add("Vento");
        c2.add("Calor");
        c2.add("Frio");
        c2.add("Chuva");
        System.out.println(c2);

        String contem = c2.contains("Granizo") ? "Contém granizo." : "Não contém granizo.";
        System.out.println(contem);

        Collections.sort(c2);
        System.out.println(c2);
        c2.remove("Frio");
        c2.remove(0);
        System.out.println(c2);

        Set<Pessoa> c3 = new HashSet<>();
        c3.add(new Pessoa("Manuela", 3056123, new Date()));
        c3.add(new Pessoa("Joao", 3056122, new Date(22, 2, 1955)));
        c3.add(new Pessoa("Maria", 3056120, new Date(3, 4, 2000)));
        c3.add(new Pessoa("Pedro", 3056121, new Date()));
        c3.add(new Pessoa("Jose", 3056119, new Date(7, 12, 2002)));
        Iterator<Pessoa> it = c3.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("----------------------------------------------");
        c3.add(new Pessoa("Jose", 3056119, new Date(7, 12, 2002)));
        for (Pessoa pessoa : c3) {
            System.out.println(pessoa);
        }
        System.out.println("----------------------------------------------");
        Set<DateYMD> c4 = new TreeSet<>();
        c4.add(new DateYMD());
        c4.add(new DateYMD(22, 2, 1955));
        c4.add(new DateYMD(3, 4, 2000));
        c4.add(new DateYMD(7, 12, 2002));
        c4.add(new DateYMD(1, 1, 1996));
        c4.add(new DateYMD());
        for (DateYMD d : c4) {
            System.out.println(d);
        }
        System.out.println("----------------------------------------------");
        Set<DateND> c5 = new TreeSet<>();
        c5.add(new DateND(0));
        c5.add(new DateND(35));
        c5.add(new DateND(4));
        c5.add(new DateND(2032));
        c5.add(new DateND(366));
        c5.add(new DateND(366));
        for (DateND d : c5) {
            System.out.println(d);
        }
    }
}
