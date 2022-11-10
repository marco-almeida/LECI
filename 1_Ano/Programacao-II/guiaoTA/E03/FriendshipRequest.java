
public class FriendshipRequest {

  private final int requesterIndex;
  private final int requestedIndex;
  private String status; // "pending", "accepted", "rejected", "cancelled"

  /**
   * Construtor de objectos da classe FriendshipRequest.
   * @param requester Indice do membro que fez o pedido.
   * @param requested Indice do destinatario do pedido.
   */
  public FriendshipRequest(int requester,int requested) {
    requesterIndex = requester;
    requestedIndex = requested;
    status = "pending";  // estado inicial: pedido feito, aguarda resposta
  }

  /**
   * Actualiza o estado do pedido de amizade.
   * @param status  Novo estado a registar
   */
  public void setStatus(String status) {
    // ... (Add necessary assertions here)

    this.status = status;
  }

  /**
   * Devolve o indice do membro que fez o pedido.
   */
  public int requester() { return requesterIndex; }

  /**
   * Devolve o indice do destinatario do pedido.
   */
  public int requested() { return requestedIndex; }


  public boolean pending() {
    return status.equals("pending");
  }

  public boolean accepted() {
    return status.equals("accepted");
  }

  public boolean active() {
    return pending() || accepted();
  }

  public boolean rejected() {
    return status.equals("rejected");
  }

  public boolean cancelled() {
    return status.equals("cancelled");
  }

}

