public class Levenshtein {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Uso: java -ea distancia <palavra1> <palavra2>");
      System.exit(1);
    }

    int d = distancia(args[0], args[1]);
    System.out.printf("\"%s\" <-> \"%s\" = %d\n", args[0], args[1], d);
  }

  public static int distancia(String p1, String p2) {
    if (p2.length() == 0) {
      return p1.length();
    }
    if (p1.length() == 0) {
      return p2.length();
    }
    char c1 = p1.charAt(0);
    char c2 = p2.charAt(0);
    String s1 = p1.substring(1, p1.length());
    String s2 = p2.substring(1, p2.length());
    if (c1 == c2) {
      return distancia(s1, s2);
    } else {
      int a = distancia(s1, p2);
      int b = distancia(p1, s2);
      int c = distancia(s1, s2);
      return 1 + Math.min(Math.min(a, b), c);
    }
  }

}
