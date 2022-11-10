// TODO: Determine whether a or b do not represent an integer
// by catching the NumberFormatException. If either one is not an integer,
// use the value 0 when computing the sum.

public class Numbers
{
   public int add(String a, String b)
   {
      int x;
      int y;
      try {
         x = Integer.parseInt(a);
      } catch (NumberFormatException err) {
         x = 0;
      }
      try {
         y = Integer.parseInt(b);
      } catch (NumberFormatException err) {
         y = 0;
      }
      
      return x + y;
   }
}