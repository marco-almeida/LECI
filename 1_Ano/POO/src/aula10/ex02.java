package aula10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ex02 {
    public static void main(String[] args) {
        Map<String, List<String>> dados = new TreeMap<>();
        add(dados, "branco", "cor da neve");
        add(dados, "branco", "descorado");
        add(dados, "branco", "palido");
        add(dados, "branco", "leite");
        add(dados, "vermelho", "cor de sangue venoso");
        add(dados, "vermelho", "cor de sangue arterial");
        add(dados, "verde", "cor da relva");
        add(dados, "azul", "cor do ceu");
        add(dados, "cinzento", "passeio");

        System.out.println(dados);
        System.out.println(dados.keySet());
        System.out.println(dados.values());
        System.out.println(randomValue(dados, "branco"));
    }

    public static void add(Map<String, List<String>> m, String k, String v) {
        if (m.putIfAbsent(k, new ArrayList<>(Arrays.asList(v))) != null) {
            m.get(k).add(v);
        }
    }

    public static void remove(Map<String, List<String>> m, String k, List<String> v) {
        m.remove(k, v);
    }

    public static String randomValue(Map<String, List<String>> m, String termo) {
        return m.get(termo).get((int) (Math.random() * (m.get(termo).size())));
    }
}
