/**
   A salary to which a bonus can be applied.
*/
public class Salary{

   private double money;
   
   
   public Salary(double money){
      this.money = money;
   }
   
   public double applyBonus(double bonus){
      return this.money = this.money * (1 + bonus);
   }
   
   public double getValue(){
      return this.money;
   }
}

