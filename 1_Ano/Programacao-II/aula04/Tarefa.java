public class Tarefa {
    private Data inicio;
    private Data fim;
    private String texto;

    public Tarefa(Data inicio, Data fim, String texto) {
        assert (inicio.compareTo(fim)) <= 0 : "Inicio nao pode ser maior que fim";
        assert (texto != null && texto != "") : "Texto não pode ser null";
        this.inicio = inicio;
        this.fim = fim;
        this.texto = texto;
    }

    public String toString() {
        return this.inicio + " --- " + this.fim + ": " + this.texto;
    }

    public Data inicio() {
        return this.inicio;
    }

    public Data fim() {
        return this.fim;
    }

    public String texto() {
        return this.texto;
    }

    public boolean intersecta(Tarefa t2) {
        if ((this.inicio.compareTo(t2.inicio()) >= 0 && this.inicio.compareTo(t2.fim()) <= 0)
                || (this.fim.compareTo(t2.inicio()) >= 0 && this.fim.compareTo(t2.fim()) <= 0)) {
            return true;
        }
        return false;
    }
}
