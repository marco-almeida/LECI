/**
   A square is a rectangle whose sides have the same length.
*/
public class Square
{
   private double length;
   
   /**
      Constructs a square with a given side length.
      @param sideLength the length of each side
   */   
   public Square(double sideLength)
   {
      this.length = sideLength;
   }
   
   /**
      Returns the area of this square.
      @return the area
   */
   public double area()
   {
      return Math.pow(this.length, 2);     
   }
   
   /**
      Grows the side length of this square.
      @param percentage the percentage by which to grow the square (for example,
      10 if the square is to be grown by 10%).
   */
   public void grow(double percentage)
   {
      this.length += length * percentage / 100;         
   }

   // This method is used for checking your work. Do not modify it.

   public static double check(double s, double p)
   {
      Square sq = new Square(s);
      sq.grow(p);
      return sq.area();
   }
}