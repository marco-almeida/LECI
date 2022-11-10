import java.io.*;

public class ListDir {
    public static void main(String[] args) throws IOException {
        File diretorio;

        if (args.length == 1) {
            diretorio = new File(args[0]);
            assert diretorio.exists() : "Must be a directory!";
        } else {
            diretorio = new File(".");
        }
        assert diretorio.canRead() : "Must be able to read directory!";
        File[] allFiles = diretorio.listFiles();
        try {
            list(allFiles);
        } catch (NullPointerException e) {
            System.err.println("Invalid Path!");
        }

    }

    public static void list(File allFiles[]) {
        for (int i = 0; i < allFiles.length; i++) {
            if (allFiles[i].isDirectory()) {
                System.out.print("D");
            } else if (allFiles[i].isFile()) {
                System.out.print("F");
            }
            if (allFiles[i].canRead()) {
                System.out.print("R");
            } else {
                System.out.print("-");
            }
            if (allFiles[i].canWrite()) {
                System.out.print("W  ");
            } else {
                System.out.print("-  ");
            }
            System.out.println(allFiles[i].getPath());
        }
    }
}
