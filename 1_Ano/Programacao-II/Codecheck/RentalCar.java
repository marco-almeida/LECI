/**
   A rental car with static methods to count rented and available cars.
*/
public class RentalCar
{
   private boolean rented;
   private static int nrent;
   private static int navail;

   /**
      Constructs a rental car.
   */
   public RentalCar()
   { 
      navail++;
   }

   /**
      Get number of cars available
      @return count of cars that are available
   */
   public static int numAvailable() 
   {
      return navail;
   }

   /**
      Get number of cars rented
      @return count of cars that are rented
   */
   public static int numRented() 
   {
      return nrent;
   }

   /**
      Try to rent this car.
      @return true if the car was successfully rented, false if it was already
      rented.
   */
   public boolean rentCar() 
   {
      if(!this.rented)
      {  
         nrent++;
         navail--;
         this.rented = true;
         return true;
      }

      return false;
   }

   /**
      Return rented car.
      @return true if the car was previously rented and is now returned,
      false if it was not previously rented.
   */
   public boolean returnCar()
   {
      if(this.rented)
      {
         nrent--;
         navail++;
         this.rented = false;
         return true;
      }

         return false;
      
   }
 
   // This method is used for checking your work. Do not modify it.

   public static String check(int n)
   {
      RentalCar[] cars = new RentalCar[n];
      for (int i = 0; i < n; i++) 
      {
         cars[i] = new RentalCar();
      }
      for (int i = 0; i < n; i = i + 2)
      {
         cars[i].rentCar();
      }
      for (int i = 0; i < n; i = i + 3)
      {
         cars[i].rentCar();
      }
      for (int i = 0; i < n; i = i + 4)
      {
         cars[i].returnCar();
      }
      return RentalCar.numAvailable() + " " + RentalCar.numRented();
   }     
}