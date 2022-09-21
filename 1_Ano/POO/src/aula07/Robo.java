package aula07;

public class Robo extends ObjetoMovel {
    private String id;
    private String tipo;
    private int golos = 0;

    public Robo(String id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public void marcarGolo() {
        this.golos++;
    }

    @Override
    public String toString() {
        return super.toString() + "Robo [golos=" + golos + ", id=" + id + ", tipo=" + tipo + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getGolos() {
        return golos;
    }

    public void setGolos(int golos) {
        this.golos = golos;
    }
    
}
