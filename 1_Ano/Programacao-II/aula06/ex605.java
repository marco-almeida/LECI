import java.util.Scanner;
import java.io.*;

public class ex605 {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Must have only 1 argument!");
            System.exit(1);
        }

        File newFile = new File(args[0]);

        if (!newFile.isDirectory()) {
            System.err.print("Error: Isn't Directory!");
            System.exit(2);
        }
        list(newFile);

    }

    public static void list(File newFile) {
        File[] files = newFile.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                System.out.println(files[i]);
            } else if (files[i].isDirectory()) {
                System.out.println(files[i]);
                list(files[i]);
            }
        }
    }
}
