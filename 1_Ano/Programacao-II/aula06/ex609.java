import java.util.InputMismatchException;

public class ex609 {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Uso: java -ea ex609 <meses> <euros> <taxa> <prestacao mensal>");
            System.exit(1);
        }
        int meses = 0;
        double euros = 0;
        double taxa = 0;
        double prestacao = 0;
        try {
            meses = Integer.parseInt(args[0]);
            euros = Double.parseDouble(args[1]);
            taxa = Double.parseDouble(args[2]);
            prestacao = Double.parseDouble(args[3]);
        } catch (NumberFormatException e) {
            System.err.println("Erro! Devem-se introduzir numeros!");
            System.exit(2);
        }
        System.out.println("(recursivo) d(" + meses + ") = " + recursivo(meses, euros, taxa, prestacao));
        System.out.println("(iterativo) d(" + meses + ") = " + iterativo(meses, euros, taxa, prestacao));
    }

    public static double recursivo(int meses, double euros, double taxa, double prestacao) {
        if (meses > 1) {
            meses--;
            euros = euros + (taxa / 100 * euros) - prestacao;
            return recursivo(meses, euros, taxa, prestacao);
        } else {
            return euros + (taxa / 100 * euros) - prestacao;
        }
    }

    public static double iterativo(int meses, double euros, double taxa, double prestacao) {
        for (int i = meses; i > 0; i--) {
            euros = euros + (taxa / 100 * euros) - prestacao;
        }
        return euros;
    }
}
