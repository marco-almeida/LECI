import static java.lang.System.*;

public class PrintArgs {

  public static void main(String[] args) {
    int[] cona = { 1, 2, 3, 4, 5 };
    printArray(cona, cona.length);
  }

  /** Imprime as N primeiras strings do array, uma por linha. */
  public static void printArray(int[] array, int N) {
    if (N > 0) {
      printArray(array, N - 1);
      out.println(array[N - 1]);
    }
  }
}
