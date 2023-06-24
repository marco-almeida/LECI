package lab06.PstEmpresa;

import java.util.ArrayList;
import java.util.List;

class Registos {
    // Data elements
    private ArrayList<Empregado> empregados; // Stores the employees

    public Registos() {
        empregados = new ArrayList<>();
    }

    public void insere(Empregado emp) {
        empregados.add(emp);
    }

    public void remove(int codigo) {
        empregados.removeIf(x -> x.codigo() == codigo);
    }

    public boolean isEmpregado(int codigo) {
        return empregados.stream().anyMatch(x -> x.codigo() == codigo);
    }

    public List<Empregado> listaDeEmpregados() {
        return empregados;
    }
}
