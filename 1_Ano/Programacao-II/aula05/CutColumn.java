import java.util.Scanner;
import java.io.*;

public class CutColumn {
    public static void main(String[] args) throws IOException {
        File fil = new File(".");
        int columnNum = 0;

        if (args.length == 2) {
            fil = new File(args[0]);
            assert fil.exists() : "File must exist!";
            assert fil.isFile() : "Must be file!";
            assert fil.canRead() : "Must be able to read file!";
            try {
                columnNum = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.print("Invalid column number");
                System.exit(2);
            }
        } else {
            System.err.println("Invalid number of arguments!");
            System.exit(2);
        }
        try {
            lerFile(fil, columnNum);
        } catch (FileNotFoundException e){
            System.err.println("Invalid file!");
        }

    }

    public static void lerFile(File fil, int columnNum) throws IOException {
        Scanner scf = new Scanner(fil);
        while (scf.hasNextLine()) {
            String linha = scf.nextLine();
            String palavras[] = linha.split("[ \t]+");
            try {
                System.out.println(palavras[columnNum-1]);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println();
            }
        }
        scf.close();
    }
}
