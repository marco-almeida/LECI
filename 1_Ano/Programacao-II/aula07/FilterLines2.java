import java.io.*;
import java.util.Scanner;
// import java.util.*;   // => "error: reference to LinkedList is ambiguous"
// java.util.LinkedList colide com p2utils.LinkedList!
import p2utils.*;

public class FilterLines2 {
    public static void main(String[] args) throws IOException {

        File fil = new File("fil.txt");

        // Criar listas para as linhas curtas, médias e longas.
        LinkedList<String> content = new LinkedList<String>();

        Scanner sf = new Scanner(fil);

        // exceções poderiam ser intercetadas e mostrar mensagem de erro.
        while (sf.hasNextLine()) {
            String line = sf.nextLine();
            if (line.length() < 20) {
                content.addFirst(line);
            } else
                content.addLast(line);
            // Guardar linha na lista apropriada, consoante o tamanho.
        }
        sf.close();

        // Escrever conteúdo das listas...
        content.print();
    }

}
