package aula08;

public class PratoVegetariano extends Prato {

    public PratoVegetariano(String nome) {
        super(nome);
    }

    @Override
    public boolean addIngrediente(Alimento a) {
        if (a instanceof AlimentoVegetariano) {
            this.getComposicao().add(a);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + " - Prato Vegetariano";
    }

}
