import static java.lang.System.*;
import java.io.*;
import java.util.Scanner;
// import java.util.*;   // => "error: reference to LinkedList is ambiguous"
// java.util.LinkedList colide com p2utils.LinkedList!
import p2utils.*;

public class FilterLines {
  public static void main(String[] args) throws IOException {

    if (args.length != 1) {
      err.printf("Usage: java -ea FilterLines text-file\n");
      exit(1);
    }
    File fil = new File(args[0]);

    // Criar listas para as linhas curtas, médias e longas.
    LinkedList<String> curtas = new LinkedList<String>();
    LinkedList<String> medias = new LinkedList<String>();
    LinkedList<String> longas = new LinkedList<String>();

    Scanner sf = new Scanner(fil);
    
    // exceções poderiam ser intercetadas e mostrar mensagem de erro.
    while (sf.hasNextLine()) {
      String line = sf.nextLine();
      if (line.length() < 20){
        curtas.addFirst(line);
      } else if (line.length() >= 20 && line.length() <= 40){
        medias.addFirst(line);
      } else 
        longas.addFirst(line);
      // Guardar linha na lista apropriada, consoante o tamanho.
    }
    sf.close();

    // Escrever conteúdo das listas...
    out.println("Curtas---|---------|---------|---------|---------");
    curtas.print();

    out.println("Medias---|---------|---------|---------|---------");
    medias.print();

    out.println("Longas---|---------|---------|---------|---------");
    longas.print();
  }
}
