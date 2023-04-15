package lab04.T1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main_fligths {
    // Scanner input = new Scanner(System.in);
    // String option = input.nextLine();

    // check if file format is correct
    public static boolean fileValidation(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String first_line = "";
        first_line = reader.readLine();
        char[] first_line_split = first_line.toCharArray();

        reader.close();

        if (first_line_split[0] == '>') {
            return true;
        }
        return false;
    }

    //show the info about a flight
    public static void fligthInfo(File file) throws IOException {
        Aviao aviao;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String first_line = "";
        first_line = reader.readLine();
        String[] first_line_split = first_line.split(" ");
        reader.close();

        if(first_line_split.length == 3){
            String[] numberSeatsExec = first_line_split[1].split("x"); 
            String[] numberSeatsTur = first_line_split[2].split("x"); 

            String codigo = first_line_split[0].substring(1);
            aviao = new Aviao(Integer.parseInt(numberSeatsExec[0]), Integer.parseInt(numberSeatsExec[1]), Integer.parseInt(numberSeatsTur[0]), Integer.parseInt(numberSeatsTur[1]) );

            System.out.printf("Código de voo %s.", codigo);
            System.out.printf("Lugares disponíveis: %d lugares em classe Executiva; %d lugares em classe Turística.", aviao.getLugaresTuristica(), aviao.getLugaresTuristica());
            System.out.println();
        }else{
            String[] numberSeatsTur = first_line_split[1].split("x"); 

            String codigo = first_line_split[0].substring(1);
            aviao = new Aviao(Integer.parseInt(numberSeatsTur[0]), Integer.parseInt(numberSeatsTur[1]) );

            System.out.printf("Código de voo %s. \n", codigo);
            System.out.printf("Lugares disponíveis: %d lugares em classe Turística. \n", aviao.getLugaresTuristica());
            System.out.println();
        }

    }

    public static void showMenuOptions() {
        System.out.println("M fligthcode -> Exibir mapa das reservas do voo");
        System.out.println(
                "F flight_code num_seats_executive num_seats_tourist -> Acrescentar um novo voo com código, lugares em executiva e lugares em turística");
        System.out.println(
                "R fligthcode class num_seats -> Acrescentar uma nova reserva a um voo com indicação do código de voo, da classe e do número de lugares");
        System.out.println("C reservation_code -> Cancelar uma reserva");
        System.out.println("Q-> Terminar programa");
    }

    public static void options() throws IOException {
        System.out.println("Escolha uma opção: (H para ajuda)");
        try (Scanner input = new Scanner(System.in)) {
            String[] option = input.nextLine().split(" ");
            while (!option[0].toUpperCase().equals("Q")) {
                switch (option[0].toUpperCase()) {
                    case "H":
                        showMenuOptions();
                        break;
                    case "I":
                        if (option.length == 2) {
                            String file = option[1];
                            File myObj = new File(file);
                            if (fileValidation(myObj)) {
                                fligthInfo(myObj);
                            }

                            else {
                                System.err.println("Ficheiro não válido!");
                                System.exit(1);
                            }
                        } else {
                            System.err.println("Ficheiro não válido!");
                            System.exit(1);
                        }

                        break;
                    case "M":

                        break;

                    case "F":
                        break;

                    case "R":
                        break;

                    case "C":
                        break;

                }
                option = input.nextLine().split(" ");
            }
            System.out.println("A terminar programa");
            System.exit(0);
        }
    }

    public static void main(String[] args) throws IOException {
        options();
    }
}
