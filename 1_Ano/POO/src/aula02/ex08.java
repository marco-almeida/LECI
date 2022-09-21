package aula02;

import java.util.Scanner;

public class ex08 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.print("Cateto A: ");
        double a = ler.nextDouble();
        System.out.print("Cateto B: ");
        double b = ler.nextDouble();
        ler.close();
        double hipotenusa = Math.hypot(a, b);
        double angulo = Math.toDegrees(Math.asin(b / hipotenusa));
        System.out.printf("Hipotenusa: %4.2f%nÂngulo: %4.2f", hipotenusa, angulo);
    }
}
