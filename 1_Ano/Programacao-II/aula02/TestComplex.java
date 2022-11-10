import static java.lang.System.out;
import java.util.Scanner;

public class TestComplex {
  static Scanner ler = new Scanner(System.in);

  // Exemplo simples de utilização da class Complex
  public static void main(String[] args) {

    Complex a;

    if (args.length < 2) {
      System.out.print("Re: ");
      double real = ler.nextDouble();
      System.out.print("Im: ");
      double imag = ler.nextDouble();
      a = new Complex(real, imag);
    } else {
      a = new Complex(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
    }
    ler.close();

    // Vamos usar métodos do objeto a
    out.printf("(%.2f+%.2fi)\n", a.real(), a.imag());
    out.println("  parte real = " + a.real());
    out.println("  parte imaginaria = " + a.imag());
    out.println("  modulo = " + a.abs());
    out.printf("  argumento = %.2f\n", a.arg());
  }
}