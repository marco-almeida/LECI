package aula07;

import Helper.Utils;

public class ex03 {
    public static void main(String[] args) {
        String nome = Utils.nextLine("Nome da Agência: ");
        String endereco = Utils.nextLine("Endereço da Agência: ");
        Agencia continental = new Agencia(nome, endereco);
        int op;

        do {
            printMenu();
            op = Utils.nextInt("");
            switch (op) {
                case 1:
                    continental.addAlojamento(adicionarAlojamento());
                    break;
                case 2:
                    continental.addViatura(adicionarViatura());
                    break;
                case 3:
                    for (Alojamento a : continental.getAlojamentos()) {
                        System.out.println(a);
                    }
                    break;
                case 4:
                    for (Carro c : continental.getViaturas()) {
                        System.out.println(c);
                    }
                    break;
                case 5:
                    checkIn(continental);
                    break;
                case 6:
                    checkOut(continental);
                    break;
                case 7:
                    levantarViatura(continental);
                    break;
                case 8:
                    entregarViatura(continental);
                    break;
            }
        } while (op != 0);
    }

    public static void printMenu() {
        System.out.println("\n\nOperações");
        System.out.println("1 - adicionar alojamento");
        System.out.println("2 - adicionar viatura");
        System.out.println("3 - listar alojamentos");
        System.out.println("4 - listar carros");
        System.out.println("5 - check in em alojamento");
        System.out.println("6 - check out em alojamento");
        System.out.println("7 - levantar viatura");
        System.out.println("8 - entregar viatura");
    }

    public static Alojamento adicionarAlojamento() {
        Utils.nextLine("");
        String codigo = Utils.nextLine("Código: ");
        String nome = Utils.nextLine("Nome: ");
        String local = Utils.nextLine("Local: ");
        double precoNoite = Utils.nextDouble("Preço por noite: ");
        double avaliacao = Utils.nextDouble("Avaliação: ");
        Alojamento aloj;
        Utils.nextLine("");
        String variacao = Utils.nextLine("Quarto de Hotel ou Apartamento: ");

        if (variacao.equalsIgnoreCase("apartamento")) {
            int numQuartos = Utils.nextInt("Número de quartos: ");
            aloj = new Apartamento(codigo, nome, local, precoNoite, avaliacao, numQuartos);
        } else {
            String tipo = Utils.nextLine("Tipo: ");
            aloj = new QuartoHotel(codigo, nome, local, precoNoite, avaliacao, tipo);
        }
        return aloj;
    }

    public static Carro adicionarViatura() {
        char classe = Utils.nextChar("Classe do carro (A a F): ");
        String motorizacao = Utils.next("Motorização do carro: ");
        return new Carro(classe, motorizacao);
    }

    public static void checkIn(Agencia agencia) {
        String op = Utils.next("Código do alojamento para dar check in: ");
        for (Alojamento a : agencia.getAlojamentos()) {
            if (a.getCodigo().equals(op)) {
                if (!a.checkIn()) {
                    System.out.println("Alojamento indisponível!");
                    return;
                } else {
                    return;
                }
            }
        }
        System.out.println("Alojamento inexistente!");
    }

    public static void checkOut(Agencia agencia) {
        String op = Utils.next("Código do alojamento para dar check out: ");
        for (Alojamento a : agencia.getAlojamentos()) {
            if (a.getCodigo().equals(op)) {
                if (!a.checkOut()) {
                    System.out.println("Check out não sucedido!");
                    return;
                } else {
                    return;
                }
            }
        }
        System.out.println("Alojamento inexistente!");
    }

    public static void levantarViatura(Agencia agencia) {
        int op = Utils.nextInt("Posição do carro na lista de viaturas: ");
        if (op > agencia.getViaturas().size() || op <= 0) {
            System.out.println("Posição inválida!");
            return;
        }
        if (!agencia.getViaturas().get(op - 1).levantar()) {
            System.out.println("Viatura indisponível!");
        } else {
            return;
        }
    }

    public static void entregarViatura(Agencia agencia) {
        int op = Utils.nextInt("Posição do carro na lista de viaturas: ");
        if (op > agencia.getViaturas().size() || op <= 0) {
            System.out.println("Posição inválida!");
            return;
        }
        if (!agencia.getViaturas().get(op - 1).entregar()) {
            System.out.println("Levantamento não sucedido!");
        } else {
            return;
        }
    }
}
