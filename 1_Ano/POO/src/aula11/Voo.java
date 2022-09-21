package aula11;

import java.time.LocalTime;

public class Voo {
    private LocalTime hora;
    private String voo;
    private String companhia;
    private String origem;
    private LocalTime atraso;
    private String obs;

    public Voo(LocalTime hora, String voo, String companhia, String origem, LocalTime atraso) {
        this.hora = hora;
        this.voo = voo;
        this.companhia = companhia;
        this.origem = origem;
        this.atraso = atraso;
        this.obs = "Previsto: " + hora.plusMinutes(atraso.getMinute());
    }

    @Override
    public String toString() {
        if (atraso.equals(LocalTime.of(0, 0))) {
            return String.format("%s  %-10s%-20s%-22s", hora, voo, companhia, origem);
        } else {
            return String.format("%s  %-10s%-20s%-22s%-9s%s", hora, voo, companhia, origem, atraso, obs);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((atraso == null) ? 0 : atraso.hashCode());
        result = prime * result + ((companhia == null) ? 0 : companhia.hashCode());
        result = prime * result + ((hora == null) ? 0 : hora.hashCode());
        result = prime * result + ((obs == null) ? 0 : obs.hashCode());
        result = prime * result + ((origem == null) ? 0 : origem.hashCode());
        result = prime * result + ((voo == null) ? 0 : voo.hashCode());
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
        Voo other = (Voo) obj;
        if (atraso == null) {
            if (other.atraso != null)
                return false;
        } else if (!atraso.equals(other.atraso))
            return false;
        if (companhia == null) {
            if (other.companhia != null)
                return false;
        } else if (!companhia.equals(other.companhia))
            return false;
        if (hora == null) {
            if (other.hora != null)
                return false;
        } else if (!hora.equals(other.hora))
            return false;
        if (obs == null) {
            if (other.obs != null)
                return false;
        } else if (!obs.equals(other.obs))
            return false;
        if (origem == null) {
            if (other.origem != null)
                return false;
        } else if (!origem.equals(other.origem))
            return false;
        if (voo == null) {
            if (other.voo != null)
                return false;
        } else if (!voo.equals(other.voo))
            return false;
        return true;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getVoo() {
        return voo;
    }

    public void setVoo(String voo) {
        this.voo = voo;
    }

    public String getCompanhia() {
        return companhia;
    }

    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public LocalTime getAtraso() {
        return atraso;
    }

    public void setAtraso(LocalTime atraso) {
        this.atraso = atraso;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

}
