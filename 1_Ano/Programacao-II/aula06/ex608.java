import java.util.Scanner;

public class ex608 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int a = ler.nextInt();
        int b = ler.nextInt();
        System.out.println(mdc(a,b));
    }

    public static int mdc (int a,int b){
        if (b == 0){
            return a;
        } else {
            return mdc(b, a % b);
        }
    }
}
