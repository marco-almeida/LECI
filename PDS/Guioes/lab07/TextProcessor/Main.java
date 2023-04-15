package lab07.TextProcessor;

public class Main {
    public static void main(String[] args) {
        final String path = "lab07/TextProcessor/files/ex1.txt";

        TextInterface j1 = new TextReader(path);
        TextInterface x1 = new NormalizationFilter(new TextReader(path));
        TextInterface x2 = new VowelFilter(new TextReader(path));
        TextInterface x3 = new CapitalizationFilter(new NormalizationFilter(new TextReader(path)));
        TextInterface x4 = new CapitalizationFilter(new TermFilter(new NormalizationFilter(new TextReader(path))));
        TextInterface[] lista = {j1, x1, x2, x3};

        for (TextInterface ji : lista) {
            System.out.println(ji.hasNext());
            System.out.println(ji.next());
            System.out.println(ji.hasNext());
            System.out.println();
        }

        while (x4.hasNext()) {
            System.out.println(x4.next());
        }
    }
}
