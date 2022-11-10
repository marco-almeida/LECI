/**
   Calculate the salary amount by applying a bonus rate.
*/

public class SalaryBonus
{
   private double salary;
   private double bonusRate;

   public SalaryBonus(double aSalary, double aRate)
   {
      //TODO: Add assert statements
      assert aSalary <= 150000;
      assert aRate <= 0.05;
      salary = aSalary;
      bonusRate = aRate;
   }
   
   /**
      The following method checks your constructor. We use the exception
      handling mechanism (see chapter 11) to determine whether (a) the
      constructor completed normally, (b) your code used an assertion to
      check a precondition violation, or (c) you failed to use an
      assertion and some other exception occurred.
   */   
   public static String check(double salaryTest, double rateTest)
   {
      try
      {
         SalaryBonus eBonus = new SalaryBonus(salaryTest,rateTest);
         return "Constructor completed normally";
      }
      catch (AssertionError error)
      {
         return "Precondition violation detected";
      }
      catch (Exception exception)
      {
         return "Exception in constructor";
      }
   }
}