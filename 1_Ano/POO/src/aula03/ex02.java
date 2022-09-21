package aula03;

import java.util.Scanner;

public class ex02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserir valor N: ");
        int n = sc.nextInt();
        sc.close();
        for (int i = n; i >= 0; i--) {
            System.out.println(i);
        }
    }
}
