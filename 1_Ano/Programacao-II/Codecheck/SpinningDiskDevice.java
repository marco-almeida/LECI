public class SpinningDiskDevice 
{
   private int lastInput;
   private int segmentLength;

   /**
      Constructs a spinning disk device.
   */
   public SpinningDiskDevice()
   {
      
   }

   /**
      Processes a new input
      @param input 0 or 1
   */
   public void add(int input)
   {
      if (input != lastInput){
         segmentLength = 1;
         lastInput = input;
      } else {
         segmentLength++;
      }
   }

   /**
      Gets the length of the current segment.
   */
   public int getSegmentLength()
   {
      return segmentLength;
   }
}