public class ReverseString {
    public static void main(String[] args) {

        for (int i = 0; i < args.length; i++) {
            reverse(args[i], args[i].length());
            System.out.println();
        }
    }

    public static void reverse(String frase, int n) {
        if (n > 0){
            System.out.print(frase.charAt(n-1));
            reverse(frase, n-1);
        }
    }
}
