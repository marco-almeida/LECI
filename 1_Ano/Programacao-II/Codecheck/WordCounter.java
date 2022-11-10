import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
   Reads a text file, line by line,
   counts the number of words per line,
   prints out each line of the poem,
   preceded by number of the number of words in that line.
*/
public class WordCounter
{
   public static void main(String[] args) throws FileNotFoundException
   {
      // Set up the input and output file names
      String inputFileName = "JackJill.txt";
      String outputFileName = "output.txt";
      File inputFile = new File (inputFileName);
      File outputFile = new File (outputFileName);

      // Construct the Scanner and PrintWriter objects
      Scanner scf = new Scanner (inputFile);
      PrintWriter pwf = new PrintWriter (outputFile);
      // your work here 
      String linha ="";
      int numPalavras = 0;
      
      // Read the input file, writing the output for each line
      while (scf.hasNextLine()){
         numPalavras = 0;
         linha = scf.nextLine();
         char arr[] = linha.toCharArray();
         
         for (int i = 0; i < arr.length; i++){
            if (arr[i] == ' '){
               numPalavras++;
            }
         }
         if (numPalavras == 0){
            pwf.printf("%d   %s\n", numPalavras , linha);
         } else {
            pwf.printf("%d   %s\n", numPalavras +1, linha);
         }
      }
      // your work here

      // Close all files
      scf.close();
      pwf.close();
      // your work here

   }
}