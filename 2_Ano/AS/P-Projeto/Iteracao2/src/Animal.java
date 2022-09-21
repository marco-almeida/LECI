import java.util.List;

public class Animal {
    private String nome;
    private Data dataNascimento;
    private String especie;
    private String raca;
    private double peso;
    private String sexo;
    private int numChip;
    private List<String> doencas;
    
    public Animal(String nome, Data dataNascimento, String especie, String raca, double peso, String sexo, int numChip,
            List<String> doencas) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.especie = especie;
        this.raca = raca;
        this.peso = peso;
        this.sexo = sexo;
        this.numChip = numChip;
        this.doencas = doencas;
    }
    
}
