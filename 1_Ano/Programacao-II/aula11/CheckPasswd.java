import java.io.IOException;
import java.io.*;
import java.util.Scanner;
import p2utils.KeyValueList;

public class CheckPasswd {
    public static void main(String[] args) throws IOException {
        KeyValueList<String> dict = new KeyValueList<String>();

        File fil = new File("senhas.txt");
        Scanner scf = new Scanner(fil);
        
        while (scf.hasNext()) {
            String key = scf.next();
            String value = scf.next();
            dict.set(key, value);
        }
        
        scf.close();
        Scanner ler = new Scanner(System.in);

        while (true) {
            System.out.print("Username: ");
            String username1 = ler.nextLine();
            System.out.print("Password: ");
            String password1 = ler.nextLine();

            if (!dict.contains(username1) || !dict.get(username1).equals(password1)) {
                System.out.println("Authentication failed!");
            } else {
            System.out.println("Authentication successful!");
            }
        }
    }
}