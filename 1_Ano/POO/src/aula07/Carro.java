package aula07;

public class Carro {
    private char classe;
    private String motorizacao;
    private boolean disponivel;

    public Carro(char classe, String motorizacao) {
        if (classe >= 'A' && classe <= 'F') {
            this.classe = classe;
            this.motorizacao = motorizacao;
            this.disponivel = true;
        }
    }

    public boolean levantar() {
        if (disponivel) {
            disponivel = false;
            return true;
        }
        return false;
    }

    public boolean entregar() {
        if (!disponivel) {
            disponivel = true;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Carro [classe=" + classe + ", motorizacao=" + motorizacao + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + classe;
        result = prime * result + ((motorizacao == null) ? 0 : motorizacao.hashCode());
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
        Carro other = (Carro) obj;
        if (classe != other.classe)
            return false;
        if (motorizacao == null) {
            if (other.motorizacao != null)
                return false;
        } else if (!motorizacao.equals(other.motorizacao))
            return false;
        return true;
    }

    public char getClasse() {
        return classe;
    }

    public void setClasse(char classe) {
        this.classe = classe;
    }

    public String getMotorizacao() {
        return motorizacao;
    }

    public void setMotorizacao(String motorizacao) {
        this.motorizacao = motorizacao;
    }
}
