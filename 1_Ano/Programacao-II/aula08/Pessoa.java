
public class Pessoa implements Comparable<Pessoa> {
  // Complete a classe.
  // ...
  private Data data;
  private String nome;

  public Pessoa(Data data, String nome) {
    this.data = data;
    this.nome = nome;
  }

  public Data data() {
    return this.data;
  }

  public String nome() {
    return this.nome;
  }

  public String toString() {
    return "(" + this.nome + " " + this.data.toString() + ")";
  }

  public int compareTo(Pessoa p2) {
    if (data.compareTo(p2.data) > 0) {
      return 1;
    }
    if (data.compareTo(p2.data) < 0) {
      return -1;
    }
    return 0;
  }
}
