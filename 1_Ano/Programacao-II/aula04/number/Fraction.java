package number;

/**
 * Tipo de dados representando uma fracção. Nesta versão pretende-se garantir um
 * invariante (interno) mais forte: as frações armazenadas devem ter sempre
 * denominador positivo. Isto permite simplificar alguns métodos.
 *
 * AVISO: Vários métodos podem dar erros devidos a overflow. (Não deve haver
 * problemas com numeradores e denominadores até 46430.)
 *
 * @author João Manuel Rodrigues 2007--2018
 */

public class Fraction implements Comparable<Fraction> {
  private int num;
  private int den;
  public static Fraction ZERO = new Fraction(0, 1);
  public static Fraction ONE = new Fraction(1, 1);

  /**
   * Cria uma nova fracção a partir de um par (numerador, denominador).
   * 
   * @param num numerador da nova fracção.
   * @param den denominador da nova fracção. <b>Exige (pré-condição):</b> den !=
   *            0.
   */
  public Fraction(int num, int den) {
    if (den < 0) {
      den = den * (-1);
      num = num * (-1);
    }
    int a = 1;
    for (int i = 1; i <= Math.abs(num) && i <= den; i++) {
      if (num % i == 0 && den % i == 0) {
        a = i;
      }
    }

    this.num = num / a;
    this.den = den / a;

  }

  /*
   * Testa o invariante do objeto. Ou seja, a propriedade que define a validade de
   * uma fração. É para testar em asserções nos métodos.
   */
  public boolean invariant() {
    if (this.den != 0) {
      return true;
    }
    return false; // O denominador não pode ser nulo!
  }

  /**
   * Converte uma string numa fracção.
   * 
   * @param str String no formato {@code "inteiro/inteiro"} representando uma
   *            fracção válida.
   * @return fracção correspondente a {@code str}.
   */
  public static Fraction parseFraction(String str) {
    String[] p = str.split("/", 2); // divide a string em até 2 partes
    int n = Integer.parseInt(p[0]); // extrai numerador
    int d = (p.length == 2) ? Integer.parseInt(p[1]) : 1;
    // se tem 2 partes, extrai denominador, senão fica d=1
    assert d != 0 : "Denominador não pode ser 0";
    return new Fraction(n, d);
  }

  /**
   * Converte a fracção numa string.
   * 
   * @return string com a representação desta fracção.
   */
  public String toString() {
    if (den == 1){
      return num +"";
    }
    return num + "/" + den;
  }

  /**
   * Devolve o numerador da fracção.
   * 
   * @return numerador desta fração.
   */
  public int num() {
    return num;
  }

  /**
   * Devolve o denominador da fracção.
   * 
   * @return denominador desta fração.
   */
  public int den() {
    return den;
  }

  /**
   * Multiplica esta fracção por outra (this * b).
   * 
   * @param b multiplicando.
   * @return fracção produto de this * b.
   */
  public Fraction multiply(Fraction b) {
    int n = num * b.num;
    int d = den * b.den;
    Fraction p = new Fraction(n, d); // product
    return p;
  }

  /**
   * Adiciona esta fracção com outra (this + b).
   * 
   * @param b fracção a adicionar a esta.
   * @return fracção soma de this + b.
   */
  public Fraction add(Fraction b) {
    int n = num * b.den + den * b.num;
    int d = den * b.den;
    Fraction s = new Fraction(n, d); // sum
    return s;
  }

  public Fraction divide(Fraction b) {
    int n = num * b.den;
    int d = den * b.num;
    Fraction p = new Fraction(n, d); // product
    return p;
  }

  public Fraction subtract(Fraction b) {
    int n = num * b.den - den * b.num;
    int d = den * b.den;
    Fraction s = new Fraction(n, d); // sum
    return s;
  }

  public boolean equals(Fraction b) {
    double numD = this.num;
    double denD = this.den;
    double numbD = b.num;
    double denbD = b.den;
    if ((numD / denD) == (numbD / denbD)) {
      return true;
    }
    return false;
  }

  public int compareTo(Fraction b) {
    double numD = this.num;
    double denD = this.den;
    double numbD = b.num;
    double denbD = b.den;
    if ((numD / denD) < (numbD / denbD)) {
      return -1;
    }
    if ((numD / denD) > (numbD / denbD)) {
      return 1;
    }
    return 0;
  }

}
