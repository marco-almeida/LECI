// YOU SHOULD NOT CHANGE THIS FILE
// NÃO DEVE ALTERAR ESTE FICHEIRO

import static java.lang.System.*;
import java.util.Scanner;
import java.util.Random;

public class TestSocialNetwork
{
  final static Scanner in = new Scanner(System.in);

  final static Random rnd = new Random();  // A random number generator

  public static void main(String[] args)
  {
    // Set the random number generator seed if there is an argument
    if (args.length > 0) rnd.setSeed(args[0].hashCode());
    
    String[] names = { "Joao","Anna","Nuno","Luis","Carlos",
                       "Antonio","Jose","Susana","Rosa","Odete",
                       "Amelia","Mario","Afonso","Leonor",
                       "Irene" };
    String[] professions = 
                     { "medico","engenheiro","professor",
                       "decorador","enfermeiro","juiz", "actor",
                       "carpinteiro","politico","padeiro",
                       "cabeleireiro","pastor","agricultor" };
    SocialNetwork sn = new SocialNetwork(10,4);

    /* Comente/descomente à medida que for testando:
    */

    out.println("sn.numMembers()=" + sn.numMembers());
    for(int i=0; i<names.length; i++) {
      int y = 1995-rnd.nextInt(35);
      String prof = professions[rnd.nextInt(professions.length)];
      sn.addMember(new Person(names[i],y,prof));
    }
    out.println("sn.numMembers()=" + sn.numMembers());


    /* Comente/descomente a medida que for testando:
     */

    out.println("sn.numRequests()=" + sn.numRequests());
    out.println("sn.maxNumRequests()=" + sn.maxNumRequests());
    for(int i=0; i<names.length; i++) {
      int np = rnd.nextInt(7);
      for(int j=i+1; j<i+np; j++) {
        sn.addFriendshipRequest(names[i],names[j%names.length]);
        int r = rnd.nextInt(3);
        if (r==1)
          sn.setRequestStatus(names[i],names[j%names.length],"accepted");
        if (r==2)
          sn.setRequestStatus(names[i],names[j%names.length],"rejected");
      }
    }
    out.println("sn.numRequests()=" + sn.numRequests());
    out.println("sn.maxNumRequests()=" + sn.maxNumRequests());
    sn.display();

    int x = rnd.nextInt(names.length);

    out.println("sn.numPendingRequests(" + names[x] + ")=" + sn.numPendingRequests(names[x]));

    out.println("sn.oldestFriend("+names[x]+"): " + sn.oldestFriend(names[x]));
  }

}
