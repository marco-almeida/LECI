import static java.lang.System.*;
public class DatasPassadas {

  public static void main(String[] args) {
    Data atual = new Data();
    Data natal = new Data(25, 12, 2020);

    while (!atual.extenso().equals(natal.extenso())){
      out.printf("%s\n", natal.extenso());
      natal.seguinte();
    }
  }

}

