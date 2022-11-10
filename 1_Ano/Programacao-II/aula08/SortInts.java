import static java.lang.System.*;
import java.io.*;
import java.util.Scanner;

public class SortInts {
    public static void main(String[] args) throws IOException {
        if (args.length <= 0) {
            err.println("Número de argumentos insuficiente!");
        }
        SortedListInt listaNum = new SortedListInt();

        for (int i = 0; i < args.length; i++) {
            File fil = new File(args[i]);

            if (!fil.exists()) {
                err.println("File doesn't exist!");
                exit(1);
            }

            Scanner scf = new Scanner(fil);

            while (scf.hasNextLine()) {
                try {
                    int a = Integer.parseInt(scf.nextLine());
                    listaNum.insert(a);
                } catch (NumberFormatException e) {
                    // nada
                }
            }
            scf.close();
        }

        while (!listaNum.isEmpty()) {
            out.println(listaNum.first());
            listaNum.removeFirst();
        }
    }
}
