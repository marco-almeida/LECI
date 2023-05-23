package lab11.Ex3;

import java.util.Scanner;

public class LibraryMain {

    public static void main(String args[]){
        Book book1 = new Book("Lord of the Rings","ISBN1",1990,"Tolkien");
        Book book2 = new Book("Java for Dummies","ISBN2",1880,"Dummie");
        Book book3 = new Book("Star Wars","ISBN3",1300,"Jorge Lucas");
        
        System.out.println("**** Library *********");
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);
        System.out.println("Options: 1-Register 2-Borrow 3-Return 4-Reservation 5-Cancel Reservation\n");


        Scanner sc = new Scanner(System.in);
        int op;
        int bk;
        Book book_temp = book1;

        while(true){
            System.out.print("Book: ");
            bk = sc.nextInt();
            System.out.print("Operation: ");
            op = sc.nextInt();

            switch(bk){
                case 1: book_temp = book1; break;
                case 2: book_temp = book2; break;
                case 3: book_temp = book3; break;
                default: System.out.println("Wrong book.");
            }

            switch(op){
                case 1: book_temp.register(); break;
                case 2: book_temp.borrow(); break;
                case 3: book_temp.returns(); break;
                case 4: book_temp.makeReservation(); break;
                case 5: book_temp.cancelReservation(); break;
                default: System.out.println("Wrong op.");
            }

            System.out.println();
        }

        


    }
    
}
