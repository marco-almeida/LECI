
class Container {

  public final int containerId;
  public final String destination;
  public final int distance;
  public final String product;
  private static int nextId = 0;

  public Container(String destin,int dist,String prod) {
    containerId = nextId;
    nextId++;
    destination = destin;
    product = prod;
    distance = dist;
  }

  public String toString() {
    return "(" + containerId +","+ destination + 
      "," + distance + "," + product + ")";
  }
}

