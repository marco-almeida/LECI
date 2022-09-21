package aula05;

public class Livro {
    private int id;
    private static int ids = 100;
    private String titulo;
    private String tipoEmprestimo;
    private boolean disponivel;

    public Livro(String titulo) {
        this.titulo = titulo;
        this.tipoEmprestimo = "NORMAL";
        this.disponivel = true;
        id = ids++;
    }

    public Livro(String titulo, String tipoEmprestimo) {
        this(titulo);
        this.tipoEmprestimo = tipoEmprestimo;
    }

    @Override
    public String toString() {
        return String.format("Livro %d; %s; %s; %b", id, titulo, tipoEmprestimo, disponivel);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (disponivel ? 1231 : 1237);
        result = prime * result + id;
        result = prime * result + ((tipoEmprestimo == null) ? 0 : tipoEmprestimo.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
        Livro other = (Livro) obj;
        if (disponivel != other.disponivel)
            return false;
        if (id != other.id)
            return false;
        if (tipoEmprestimo == null) {
            if (other.tipoEmprestimo != null)
                return false;
        } else if (!tipoEmprestimo.equals(other.tipoEmprestimo))
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipoEmprestimo() {
        return tipoEmprestimo;
    }

    public void setTipoEmprestimo(String tipoEmprestimo) {
        this.tipoEmprestimo = tipoEmprestimo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
