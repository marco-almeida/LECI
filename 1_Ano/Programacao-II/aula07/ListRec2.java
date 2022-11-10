import static java.lang.System.*;
import java.io.File;

import p2utils.LinkedList;

public class ListRec2 {

  public static void main(String[] args) {

    File dir = new File(".");

    // Verificar entrada
    if (!dir.exists()) {
      err.printf("ERRO: %s nao existe!\n", dir);
      exit(2);
    }

    LinkedList<File> files = recListFiles(dir);
    files.print();
  }

  /**
   * Devolve uma lista com o conteúdo de um directório f e de todos os seus
   * subdirectórios recursivamente.
   */
  public static LinkedList<File> recListFiles(File f) {
    LinkedList<File> listaDevolvida = new LinkedList<>();
    File[] files = f.listFiles();

    for (int i = 0; i < files.length; i++) {
      if (files[i].isFile()) {
        listaDevolvida.addLast(files[i]);
      } else if (files[i].isDirectory()) {
        listaDevolvida = listaDevolvida.concatenate(recListFiles(files[i]));
      }
    }
    return listaDevolvida;
  }
}
