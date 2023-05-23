package lab11.Ex5;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("ERROR: Invalid number of arguments!");
            System.exit(0);
        }

        final boolean recursion = args.length == 2;

        String dirName = recursion ? args[1] : args[0];
        Path path = Paths.get(dirName);
        AtomicLong size = new AtomicLong(0);

        try {
            Files.walkFileTree(path, new SimpleFileVisitor<>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {

                    if (recursion) {
                        if (Files.isDirectory(file)) {
                            System.out.printf("Dir -> %s: %d bytes\n", file, attr.size());
                        } else {
                            System.out.printf("File -> %s: %d bytes\n", file, attr.size());
                        }
                        size.addAndGet(attr.size());
                        return FileVisitResult.CONTINUE;
                    } else {
                        if (Files.isRegularFile(file)) {
                            System.out.printf("File -> %s: %d bytes\n", file, attr.size());
                            size.addAndGet(attr.size());
                            return FileVisitResult.CONTINUE;
                        }
                    }
                    System.out.println("Total: " + size + " bytes");
                    return FileVisitResult.SKIP_SUBTREE;
                }
            });

        } catch (Exception e) {
            System.out.println("ERROR: Can't parse the tree!");
            System.exit(0);
        }
    }
}
