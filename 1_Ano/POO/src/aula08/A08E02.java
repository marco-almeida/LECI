package aula08;

public class A08E02 {

	public static void main(String[] args) {
		Ementa ementa = new Ementa("Especial Primavera", "Snack da UA");
		Prato[] pratos = new Prato[DiaSemana.values().length];
		for (int i = 0; i < pratos.length; i++) {
			pratos[i] = randPrato(i + 1);
			System.out.println("A sair .. " + pratos[i]);

			// Adiciona 2 ingredientes a cada prato
			int ingred = 1;
			do {
				Alimento aux = randAlimento();
				if (pratos[i].addIngrediente(aux)) {
					System.out.println("\tIngrediente " + ingred + " adicionado: " + aux);
					ingred++;
				} else
					System.out.println("\tERRO: não é possível adicionar Ingrediente " + ingred + ": " + aux);
			} while (ingred < 3);

			ementa.addPrato(pratos[i], DiaSemana.getEnum(i));
		}
		System.out.println("\nEmenta final\n--------------------");
		System.out.println(ementa);
	}

	public static Alimento randAlimento() {
		Alimento res = null;
		switch ((int) (Math.random() * 4)) {
			case 0:
				res = new Carne(VariedadeCarne.FRANGO, 22.3, 345.3, 300);
				break;
			case 1:
				res = new Peixe(TipoPeixe.CONGELADO, 31.3, 25.3, 200);
				break;
			case 2:
				res = new Legume("Couve Flor", 21.3, 22.4, 150);
				break;
			case 3:
				res = new Cereal("Milho", 19.3, 32.4, 110);
				break;
		}
		return res;
	}

	public static Prato randPrato(int i) {
		Prato res = null;
		switch ((int) (Math.random() * 3)) {
			case 0:
				res = new Prato("combinado n." + i);
				break;
			case 1:
				res = new PratoVegetariano("combinado n." + i);
				break;
			case 2:
				res = new PratoDieta("combinado n." + i, 90.8);
				break;
		}
		return res;
	}

}
