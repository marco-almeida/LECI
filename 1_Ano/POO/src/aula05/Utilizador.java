package aula05;

import java.util.ArrayList;

public class Utilizador {
    private String nome;
    private int nMec;
    private String curso;
    private ArrayList<Integer> livrosRequisitados;

    public Utilizador(String nome, int nMec, String curso) {
        this.nome = nome;
        this.nMec = nMec;
        this.curso = curso;
        livrosRequisitados = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Aluno: " + nMec + "; " + nome + "; " + curso + "; " + livrosRequisitados.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((curso == null) ? 0 : curso.hashCode());
        result = prime * result + ((livrosRequisitados == null) ? 0 : livrosRequisitados.hashCode());
        result = prime * result + nMec;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
        Utilizador other = (Utilizador) obj;
        if (curso == null) {
            if (other.curso != null)
                return false;
        } else if (!curso.equals(other.curso))
            return false;
        if (livrosRequisitados == null) {
            if (other.livrosRequisitados != null)
                return false;
        } else if (!livrosRequisitados.equals(other.livrosRequisitados))
            return false;
        if (nMec != other.nMec)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getnMec() {
        return nMec;
    }

    public void setnMec(int nMec) {
        this.nMec = nMec;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public ArrayList<Integer> getLivrosRequisitados() {
        return livrosRequisitados;
    }

    public void setLivrosRequisitados(ArrayList<Integer> livrosRequisitados) {
        this.livrosRequisitados = livrosRequisitados;
    }
}
