package aula05;

import java.util.ArrayList;
import java.util.Scanner;

public class ex05 {
    public static Utilizador[] alunos = new Utilizador[100];
    public static Livro[] catalogo = new Livro[100];
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int op;
        do {
            printMenu();
            op = input.nextInt();
            switch (op) {
                case 1:
                    signUp(input);
                    break;
                case 2:
                    removeUser(input);
                    break;
                case 3:
                    printAllUsers();
                    break;
                case 4:
                    registerBook(input);
                    break;
                case 5:
                    printAllBooks();
                    break;
                case 6:
                    borrow(input);
                    break;
                case 7:
                    returnBook(input);
            }
        } while (op != 8);

        input.close();
    }

    public static void printMenu() {
        System.out.println("\n\n1 - inscrever utilizador");
        System.out.println("2 - remover utilizador ");
        System.out.println("3 - imprimir lista de utilizadores");
        System.out.println("4 - registar um novo livro");
        System.out.println("5 - imprimir lista de livros");
        System.out.println("6 - emprestar");
        System.out.println("7 - devolver");
        System.out.println("8 - sair");
    }

    public static void signUp(Scanner input) {
        System.out.print("Nome: ");
        String nome = input.nextLine();
        nome = input.nextLine();
        System.out.print("nMec: ");
        int nMec = input.nextInt();
        System.out.print("Curso: ");
        String curso = input.nextLine();
        curso = input.nextLine();

        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] == null) {
                alunos[i] = new Utilizador(nome, nMec, curso);
                break;
            }
        }
    }

    public static void removeUser(Scanner input) {
        System.out.print("nMec de utilizador a remover: ");
        int nMec = input.nextInt();
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] == null) {
                continue;
            }
            if (alunos[i].getnMec() == nMec) {
                alunos[i] = null;
                break;
            }
        }
    }

    public static void printAllUsers() {
        for (Utilizador u : alunos) {
            if (u == null) {
                continue;
            }
            System.out.println(u);
        }
    }

    public static void registerBook(Scanner input) {
        System.out.print("Titulo: ");
        String titulo = input.nextLine();
        titulo = input.nextLine();
        System.out.print("Tipo de emprestimo: ");
        String tipoEmprestimo = input.nextLine();

        for (int i = 0; i < catalogo.length; i++) {
            if (catalogo[i] == null) {
                catalogo[i] = new Livro(titulo, tipoEmprestimo);
                break;
            }
        }
    }

    public static void printAllBooks() {
        for (Livro u : catalogo) {
            if (u == null) {
                continue;
            }
            System.out.println(u);
        }
    }

    public static void borrow(Scanner input) {
        printAllUsers();
        System.out.print("nMec do Aluno a fazer o emprestimo: ");
        int nMec = input.nextInt();
        printAllBooks();
        System.out.print("ID do livro a ser emprestado: ");
        int id = input.nextInt();
        //
        for (Livro l : catalogo) {
            if (l == null) {
                continue;
            }
            if (l.getId() == id) {
                for (Utilizador u : alunos) {
                    if (u == null) {
                        continue;
                    }
                    if (u.getnMec() == nMec) {
                        if (u.getLivrosRequisitados().size() < 3 && l.isDisponivel()
                                && !l.getTipoEmprestimo().equals("CONDICIONADO")) {
                            l.setDisponivel(false);
                            ArrayList<Integer> temp = u.getLivrosRequisitados();
                            temp.add(id);
                            u.setLivrosRequisitados(temp);
                        }
                        break;
                    }
                }
            }
        }
    }

    public static void returnBook(Scanner input) {
        printAllUsers();
        System.out.print("nMec do Aluno a fazer a devolução: ");
        int nMec = input.nextInt();
        printAllBooks();
        System.out.print("ID do livro a ser devolvido: ");
        int id = input.nextInt();
        // encontrar livro a ser devolvido
        for (Livro l : catalogo) {
            if (l == null) {
                continue;
            }
            if (l.getId() == id) {
                for (Utilizador u : alunos) {
                    if (u == null) {
                        continue;
                    }
                    // encontrar aluno que vai devolver
                    if (u.getnMec() == nMec) {
                        if (u.getLivrosRequisitados().contains(l.getId())) {
                            l.setDisponivel(true);
                            ArrayList<Integer> temp = u.getLivrosRequisitados();
                            for (int i = 0; i < temp.size(); i++) {
                                if (temp.get(i) == id) {
                                    temp.remove(i);
                                }
                            }
                            u.setLivrosRequisitados(temp);
                        }
                        break;
                    }
                }
            }
        }
    }
}
