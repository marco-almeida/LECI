
// Representa uma encomenda de um cliente para certa quantidade de um produto.
public class Order {

  // Os atributos vão ser fixos (final), por isso podem ficar public...
  public final String clientName;
  public final String prodName;
  public final int quantity;

  public Order(String client, String prod, int quant) {
    prodName = prod;
    clientName = client;
    quantity = quant;
  }

  public String toString() {
    return String.format("Order(%s, %s, %d)", clientName, prodName, quantity);
  }

}
