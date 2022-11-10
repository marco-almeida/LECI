
import static java.lang.System.*;
import java.io.*;
import p2utils.*;

public class TestLinkedList
{

  public static void main(String[] args) throws IOException
  {
    LinkedList<Integer> lst = new LinkedList<Integer>();

    for(int i=0; i<7; i++) {
      lst.addFirst(i);
      lst.addFirst(myRandom(8));
      lst.addLast(100+i);
    }

    out.println(lst.toString());

    out.println(lst.count(5));

    out.println(lst.cloneReplace(5,99));  //.toString() implícito!

    out.println(lst.indexOf(5));

    out.println(lst.indexOf(5000));

    out.println(lst.cloneSublist(7,14));

    out.println(lst.cloneExceptSublist(7,14));

    lst.removeSublist(7,14);
    out.println(lst);
    out.println(lst.size());

    lst.removeSublist(12,14);
    out.println(lst);
    out.println(lst.size());
    out.println(lst.last());

  }   

  static int myRandom(int n) {
    return (int)(Math.random()*n);
  }
}

