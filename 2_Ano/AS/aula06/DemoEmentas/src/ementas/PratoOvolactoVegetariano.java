package ementas;

public class PratoOvolactoVegetariano extends Prato {

    public PratoOvolactoVegetariano(String nome, double preco) {
        super(nome, preco);
    }

    @Override
    public boolean adicionarIngrediente(Alimento a) {
        if (a instanceof OvoLacto)
            return super.adicionarIngrediente(a);
        return false;
    }
}