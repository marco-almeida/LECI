public class Tarefa {
    private Data inicio;
    private Data fim;
    private String texto;

    public Tarefa(Data inicio, Data fim, String texto){
        this.inicio = inicio;
        this.fim = fim;
        this.texto = texto;
    }

    public String toString() {
        return String.format("%s\t%s\t%s", inicio, fim, texto);
    }

    public Data fim(){
        return this.fim;
    }

    public Data inicio(){
        return this.inicio;
    }

    public String texto(){
        return this.texto;
    }
}
