package lab11.Ex3M;

import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Book b1 = new Book("b1", 1, 2001, "eu");
        Book b2 = new Book("b2", 2, 2002, "tu");
        Book b3 = new Book("b3", 3, 2003, "ele");
        Scanner sc = new Scanner(System.in);
        List<Book> list = List.of(b1, b2, b3);
        while (true) {
            System.out.println("*** Biblioteca ***");
            for (Book b : list) {
                System.out.println(b);
            }
            System.out.print(
                    ">> <livro>, <operação: (1)regista; (2)requisita; (3)devolve; (4)reserva; (5)cancela\n\n>> ");
            String[] input = sc.nextLine().split(",");
            int bookIsbn = Integer.parseInt(input[0]);
            int op = Integer.parseInt(input[1]);

            switch (op) {
                case 1 -> list.get(bookIsbn - 1).register();
                case 2 -> list.get(bookIsbn - 1).request();
                case 3 -> list.get(bookIsbn - 1).returnBook();
                case 4 -> list.get(bookIsbn - 1).reserve();
                case 5 -> list.get(bookIsbn - 1).cancelReservation();
                default -> throw new RuntimeException("Wrong input");
            }
        }
    }
}
