package aula03;

import java.util.Scanner;

public class ex06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        int mes = 0;
        int ano = 0;
        boolean goahead = false;
        while (!goahead) {
            System.out.print("Inserir data composta separada pelo símbolo ':' (MM:YYYY): ");
            String data = sc.next();
            try {
                mes = Integer.parseInt(data.split(":")[0]);
                ano = Integer.parseInt(data.split(":")[1]);
                if (mes > 12 || mes < 1) {
                    throw new Exception();
                }
                goahead = true;
                sc.close();
            } catch (Exception e) {
                System.out.println("Data inválida!\n");
            }
        }
        if (anoBissexto(ano)) {
            days[1] = 29;
        }
        System.out.printf("Existem %d dias na data de %02d-%04d.", days[mes - 1], mes, ano);
    }

    public static boolean anoBissexto(int ano) {
        return ((ano % 400 == 0) || ((ano % 4 == 0) && (ano % 100 != 0)));
    }
}
