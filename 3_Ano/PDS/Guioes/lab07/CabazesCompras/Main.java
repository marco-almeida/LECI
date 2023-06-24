package lab07.CabazesCompras;

public class Main {
    public static void main(String[] args) {
        Caixa principal = new Caixa("Principal", 4);
        Caixa top = new Caixa("Topo", 2);
        Caixa bot = new Caixa("Especialidades", 2);
        top.add(new Bebida("Vinho Reserva UA 2017", 6));
        top.add(new Bebida("Vinho Reserva UA 2018", 6));
        principal.add(top);
        principal.add(bot);
        bot.add(new Conserva("Atum à Algarvia", 3));
        bot.add(new Doce("Morango", 2));
        top.add(new Caixa("Interior", 1));
        top.add(new Conserva("Sardinhas em Azeite", 5));
        principal.draw();
    }
}
