import java.util.Scanner;
import java.io.*;
import java.io.PrintWriter;

public class Ex03 {
    public static Scanner ler = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        assert args.length == 2 : "Must have 2 args";
        if (args.length != 2){
            System.err.print("Must have 2 args");
            System.exit(3);
        }
        File firstFile = new File(args[0]);
        assert firstFile.exists() : "First Argument must be a file!";
        if (!firstFile.exists()){
            System.err.printf("First file %s does not exist!", args[0]);
            System.exit(2);
        }
        assert firstFile.canRead() : "Must be able to read file!";
        File secondFile = new File(args[1]);

        while (secondFile.isFile()) {
            System.out.println("Second file is already a file. Do you wish to overwrite its contents?(Y/N)");
            String opcao = ler.nextLine();
            if (opcao.equals("y") || opcao.equals("Y")) {
                assert secondFile.canWrite() : "Need to be able to write in second file!";
                break;
            } else {
                System.out.println("Select new File!");
                String novoF = ler.nextLine();
                secondFile = new File(novoF);
            }
        }
        ler.close();
        File path = new File("C:/Users/marco/Desktop/uni/Programacao2/aula05");
        assert path.canWrite() : "Need to be able to write in second file's directory!";
        PrintWriter pwf = new PrintWriter(secondFile);
        Scanner scf = new Scanner(firstFile);

        while (scf.hasNextLine()) {
            pwf.println(scf.nextLine());
        }
        scf.close();
        pwf.close();
    }
}
