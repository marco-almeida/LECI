package lab01;

public class test {
    public static void main(String[] args) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";

        System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
}
}
