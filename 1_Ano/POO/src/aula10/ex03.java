package aula10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ex03 {
    public static void main(String[] args) {
        System.out.println(mostradeiro("Hello World"));
    }

    public static String mostradeiro(String str) {
        Map<Character, ArrayList<Integer>> mapa = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (mapa.putIfAbsent(str.charAt(i), new ArrayList<>(Arrays.asList(i))) != null) {
                mapa.get(str.charAt(i)).add(i);
            }
        }
        return mapa.toString();
    }
}
