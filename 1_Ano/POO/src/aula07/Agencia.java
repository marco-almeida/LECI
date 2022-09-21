package aula07;

import java.util.ArrayList;
import java.util.List;

public class Agencia {
    private String nome;
    private String endereco;
    private List<Alojamento> alojamentos;
    private List<Carro> viaturas;

    public Agencia(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.alojamentos = new ArrayList<>();
        this.viaturas = new ArrayList<>();
    }

    public void addAlojamento(Alojamento alo) {
        alojamentos.add(alo);
    }

    public void addViatura(Carro car) {
        viaturas.add(car);
    }

    @Override
    public String toString() {
        return "Agencia [alojamentos=" + alojamentos + ", endereco=" + endereco + ", nome=" + nome + ", viaturas="
                + viaturas + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alojamentos == null) ? 0 : alojamentos.hashCode());
        result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((viaturas == null) ? 0 : viaturas.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Agencia other = (Agencia) obj;
        if (alojamentos == null) {
            if (other.alojamentos != null)
                return false;
        } else if (!alojamentos.equals(other.alojamentos))
            return false;
        if (endereco == null) {
            if (other.endereco != null)
                return false;
        } else if (!endereco.equals(other.endereco))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (viaturas == null) {
            if (other.viaturas != null)
                return false;
        } else if (!viaturas.equals(other.viaturas))
            return false;
        return true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Alojamento> getAlojamentos() {
        return alojamentos;
    }

    public void setAlojamentos(List<Alojamento> alojamentos) {
        this.alojamentos = alojamentos;
    }

    public List<Carro> getViaturas() {
        return viaturas;
    }

    public void setViaturas(List<Carro> viaturas) {
        this.viaturas = viaturas;
    }

}
