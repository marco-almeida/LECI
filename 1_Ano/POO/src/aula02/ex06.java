package aula02;

import java.util.Scanner;

public class ex06 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.print("Segundos: ");
        int s = ler.nextInt();
        ler.close();
        int horas = s / 3600;
        int minutos = (s / 60) % 60;
        int segundos = s % 60;
        System.out.printf("%02d:%02d:%02d", horas, minutos, segundos);
    }
}
