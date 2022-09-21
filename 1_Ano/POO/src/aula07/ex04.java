package aula07;

public class ex04 {
    public static void main(String[] args) {
        Bola bola = new Bola("preto");
        Robo robo0 = new Robo("0", "Redes");
        Robo robo1 = new Robo("1", "Medio");
        Robo robo2 = new Robo("2", "Avancado");
        Robo robo3 = new Robo("3", "Redes");
        Robo robo4 = new Robo("4", "Medio");
        Robo robo5 = new Robo("5", "Medio");
        Robo[] robos012 = { robo0, robo1, robo2 };
        Equipa equipa0 = new Equipa("equipa0", "0", robos012);
        Robo[] robos345 = { robo3, robo4, robo5 };
        Equipa equipa1 = new Equipa("equipa0", "0", robos345);
        Jogo jogo = new Jogo(new Equipa[] { equipa0, equipa1 }, bola, 60);
        marcarGolo(robo0, equipa0, equipa1, jogo);
        bola.move(3, 4);
        robo0.move(5, 0);
        marcarGolo(robo4, equipa1, equipa0, jogo);
        bola.move(5, 4);
        robo4.move(5, 21);
        bola.move(3, 4);
        robo0.move(5, 0);
        marcarGolo(robo4, equipa1, equipa0, jogo);
        bola.move(5, 4);
        robo4.move(5, 21);
        bola.move(3, 4);
        robo0.move(5, 0);
        marcarGolo(robo4, equipa1, equipa0, jogo);
        bola.move(5, 4);
        robo4.move(5, 21);
        bola.move(3, 4);
        robo0.move(5, 0);
        marcarGolo(robo4, equipa1, equipa0, jogo);
        bola.move(5, 4);
        robo4.move(5, 21);
        bola.move(3, 4);
        robo0.move(5, 0);
        marcarGolo(robo4, equipa1, equipa0, jogo);
        bola.move(5, 4);
        robo4.move(5, 21);
        System.out.println(jogo);
    }

    public static void marcarGolo(Robo r, Equipa e, Equipa v, Jogo j) {
        r.marcarGolo();
        e.setGolosMarcados(e.getGolosMarcados() + 1);
        v.setGolosSofridos(v.getGolosSofridos() + 1);
        j.setTempoDecorrido(j.getTempoDecorrido() + 10);
        if (j.getDuracao() <= j.getTempoDecorrido()) {
            System.out.println("Jogo acabou.");
            // aqui nao ha empates
            String a = e.getGolosMarcados() > e.getGolosSofridos() ? "Ganhou a equipa 1" : "Ganhou a equipa 2";
            System.out.println(a);
        }
    }
}
