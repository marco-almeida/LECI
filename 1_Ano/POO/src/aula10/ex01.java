package aula10;

import java.util.HashMap;
import java.util.Map;

public class ex01 {
    public static void main(String[] args) {
        Map<String, String> dados = new HashMap<>();
        add(dados, "branco", "cor da neve");
        add(dados, "vermelho", "cor do sangue");
        add(dados, "verde", "cor da relva");
        add(dados, "azul", "cor do ceu");
        add(dados, "cinzento", "passeio");
        System.out.println(dados);
        System.out.println(dados.keySet());
        System.out.println(dados.values());
    }

    public static void add(Map<String, String> m, String k, String v) {
        m.put(k, v);
    }

    public static void remove(Map<String, String> m, String k, String v) {
        m.remove(k, v);
    }
}
