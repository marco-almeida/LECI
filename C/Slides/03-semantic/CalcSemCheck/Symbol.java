import static java.lang.System.*;
import java.util.Scanner;

public abstract class Symbol<T>
{
   public Symbol(String name, Type type) {
      assert name != null;
      assert type != null;

      this.name = name;
      this.type = type;
   }

   public void setValue(T value) {
      assert value != null;

      this.value = value;
   }

   public String name(){
      return name;
   }

   public Type type(){
      return type;
   }

   public T value(){
      return value;
   }

   protected final String name;
   protected final Type type;
   protected T value;
}

