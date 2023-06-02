import static java.lang.System.*;
import java.util.Scanner;

public abstract class Type
{
   protected Type(String name) {
      assert name != null;
      this.name = name;
   }

   public String name() {
      return name;
   }

   public boolean subtype(Type other) {
      return name.equals(other.name());
   }

   protected final String name;
}

