/**
   Describes a person who will receive mail.
*/
public class Person
{
   /*
      TODO: To increase cohesion, reimplement this class by using
      the Address class. Do not modify the main method that is used
      for checking.
   */
   
   /**
      Constructs a person. 
      @param aName the person's name
      @param aStreet the street
      @param aCity the city
      @param aState the two-letter state code
      @param aZip the ZIP postal code
   */
   public Person(String aName, Address address)
   {  
      name = aName;
      this.address = address;
   }   

   /**
      Formats the person's name and address for mailing.
      @return a string suitable for printing on a mailing label
   */
   public String formatForMailing()
   {  
      return name + "\n" + address.format();
   }
   
   private String name;
   private Address address;
   
   // This method is used for checking your work. Do not modify it

   public static void main(String[] args)
   {
      Person harry = new Person("Harold J. Hacker",
         new Address("123 Main Street", "Anytown", "NY", "12345"));
      System.out.println(harry.formatForMailing());
   }   
}