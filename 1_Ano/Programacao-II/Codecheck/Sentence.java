public class Sentence
{
   /**
      Constructs a sentence.
      @param text the text of the sentence. Words must be separated
      by single spaces, and the sentence must end in a punctuation mark
      (. ? !)
   */
   private String[] words;
   private String punctuation;
   
   public Sentence(String text)
   {
      // TODO: Add assert statements
         int n = text.length();
         assert n > 0;
         String punctuation = text.substring(n - 1, n);
         words = text.substring(0, n - 1).split(" ");
         
         assert (punctuation.equals(".") || punctuation.equals("?") || punctuation.equals("!"));
         for (int i = 0; i < n; i++){
            if (text.charAt(i) == ' '){
               assert text.charAt(i+1) != ' ';
            }
         }
      }
   
   /**
      Gets the number of words in this sentence.
      @return the number of words
   */
   public int getWordCount()
   {
      return words.length;
   }
   
   /**
      Returns a word in this sentence.
      @param the index of the word (must be at least 0 and less than the
      word count)
      @return the ith word
   */
   public String getWord(int i)
   {
      return words[i];
   }
   
   public String toString()
   {
      String r = "";
      for (String w : words)
      {
         if (r.length() > 0) r += " ";
         r += w;
      }
      return r + punctuation;
   }
   
   /*
      The following method checks your constructor. We use the exception 
      handling mechanism (see chapter 11) to determine whether (a) the 
      constructor completed normally, (b) your code used an assertion to
      check a precondition violation, or (c) you failed to use an 
      assertion and some other exception occurred.
   */
   
   public static String check(String text)
   {
      try
      {
         Sentence s = new Sentence(text);
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