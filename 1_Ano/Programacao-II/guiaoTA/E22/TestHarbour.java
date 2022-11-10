
import static java.lang.System.*;
import java.util.Scanner;

public class TestHarbour {

  static final int nOfDocks = 5; // number of docks
  static final int maxStack = 6; // maximum number of containers in a stack
  final static Scanner in = new Scanner(System.in);

  static final String[] products = 
                      { "frango", "vaca", //"yogurte", "queijo",
                        "computador","caneta", //"caderno","livro",
                        "agrafador","pente","escova" };

  static final String[] cities = 
                      { "London","Hamburg","Amsterdam","Marseille",
                        "Sines","Lisboa","Matosinhos",
                        "New York","San Francisco","Rio","Cape Town" };

  static final int[] distances =
                      { 1000,1300,1200,1100,
                        600,500,100,
                        5000,15000,8000,10000 };


  public static void main(String[] args) {

    Harbour h = new Harbour(nOfDocks,maxStack);

    do {
      Container c;
      int dockId = myRandom(nOfDocks);
      if (myRandom(2)==0) { // randomly select whether to 
        // enter a new container or not
        c = randomContainer();
        h.enterContainer(dockId,c);
        out.printf("*-- Entering container in dock %d: %s\n",dockId,c);
      }
      else if (h.stackEmpty(dockId)) {
        if (!h.accessExtensionEmpty(dockId)) {
          h.moveFromAccessToStack(dockId);
          out.printf("**-  Moved some containers from access to stack in dock %d\n",dockId);
        }
        else out.printf("--- Nothing done in this iteration\n");
      }
      else {
        c = h.shipContainer(dockId);
        out.printf("*** Shipping container from dock %d: %s\n",dockId,c);
      }

      out.println("    Total number of containers: "+h.count());
      h.displayDocks();
      out.println("Current destinations: ");
      String[] dests = h.currentDestinations();
      for(int i=0; i<dests.length; i++) out.print("'"+dests[i]+"' ");
      out.println();
      out.print("Number of containers per destination: ");
      for(int i=0; i<dests.length; i++) 
        out.print(dests[i]+":"+h.countToDestination(dests[i])+" ");
      out.println();

      in.nextLine(); // for a pause in output
    } while (in.hasNextLine());
  }

  /**
   * Returns a random number between 0 and n-1
   */
  static int myRandom(int n) {
    return (int)(Math.random()*n);
  }

  /**
   * Returns a container with random information
   */
  static Container randomContainer() {
    int p = myRandom(products.length);
    int c = myRandom(cities.length);
    return new Container(cities[c],distances[c],products[p]);
  }
}

