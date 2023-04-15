package lab01;

import java.util.List;

import Utils.Utils;

public class WSSolver {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: lab01/java WSSolver [filename.txt]");
            System.exit(1);
        }

        String filename = args[0];
        List<String> fileLines = Utils.readFile(filename);
        SopaDeLetras sopa = new SopaDeLetras(fileLines); // validates file
        System.out.println(sopa.getResults());
    }
}
