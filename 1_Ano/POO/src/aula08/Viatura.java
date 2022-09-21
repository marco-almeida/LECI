package aula08;

public abstract class Viatura implements KmPercorridosInterface, Comparable<Viatura> {
    private String matricula;
    private String marca;
    private String modelo;
    private int potencia;
    private int kmPercorridos;
    private int ultimoTrajeto;

    protected Viatura(String matricula, String marca, String modelo, int potencia) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.potencia = potencia;
        this.kmPercorridos = 0;
        this.ultimoTrajeto = 0;
    }

    @Override
    public int distanciaTotal() {
        return this.kmPercorridos;
    }

    @Override
    public void trajeto(int quilometros) {
        this.kmPercorridos += quilometros;
        this.ultimoTrajeto = quilometros;
    }

    @Override
    public int ultimoTrajeto() {
        return this.ultimoTrajeto;
    }

    @Override
    public String toString() {
        return "Viatura [kmPercorridos=" + kmPercorridos + ", marca=" + marca + ", matricula=" + matricula + ", modelo="
                + modelo + ", potencia=" + potencia + ", ultimoTrajeto=" + ultimoTrajeto + "] ";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + kmPercorridos;
        result = prime * result + ((marca == null) ? 0 : marca.hashCode());
        result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
        result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
        result = prime * result + potencia;
        result = prime * result + ultimoTrajeto;
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
        Viatura other = (Viatura) obj;
        if (kmPercorridos != other.kmPercorridos)
            return false;
        if (marca == null) {
            if (other.marca != null)
                return false;
        } else if (!marca.equals(other.marca))
            return false;
        if (matricula == null) {
            if (other.matricula != null)
                return false;
        } else if (!matricula.equals(other.matricula))
            return false;
        if (modelo == null) {
            if (other.modelo != null)
                return false;
        } else if (!modelo.equals(other.modelo))
            return false;
        if (potencia != other.potencia)
            return false;
        if (ultimoTrajeto != other.ultimoTrajeto)
            return false;
        return true;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    @Override
    public int compareTo(Viatura v){
        return this.potencia - v.getPotencia();
    }
}
