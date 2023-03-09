
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.FileWriter;
import java.io.File;

public class WSGenerator {
    public static FileWriter WriteinFile;

    public static void main(String[] args) throws IOException {
        String Inputfile = null, OutputFile = null;
        FileWriter WriteinFile = null;
        int Soup_Size = 0;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-i":
                    Inputfile = args[i + 1]; // o nome do ficheiro vem a seguir ao parametro -i
                    continue;
                case "-s":
                    Soup_Size = Integer.parseInt(args[i + 1]);
                    continue;
                case "-o": // read the output file
                    OutputFile = args[i + 1];
                    continue;
            }
        }

        // Criar o ficheiro de output com o nome passado como parametro
        if (OutputFile != null) {
            File file = new File(OutputFile);
            file.createNewFile();
            WriteinFile = new FileWriter(file);
        }

        if (Soup_Size > 40) { // Verificar o tamanho maximo que o puzzle pode ter
            System.out.println("O tamanho máximo do puzzle é 40.");
            System.exit(0);
        }

        List<String> words = ReadWordsFromFile(Inputfile);

        Validation validation = new Validation(words);
        validation.WordsinLowerCaseOrBoth(words); // Requisito de entrada 3
        validation.WordsAlphaChars(words); // Requisito de entrada 4
        validation.AtLeast3Characters(words); // Requisito de entrada 7
        words = validation.BiggerWordWithSamePrefix(words); // Requisito de entrada 9

        CreatePuzzle createPuzzle = new CreatePuzzle(words, Soup_Size);
        List<ArrayList<String>> puzzle = createPuzzle.puzzle();

        Solver solver = new Solver(puzzle, words);
        solver.solve();

        // print e escrita do ficheiro do puzzle feito
        String printPuzzle = solver.toStringPuzzle();
        if (WriteinFile != null){
        WriteinFile.write(printPuzzle); // escrever no arquivo em vez de imprimir no console
        WriteinFile.flush(); // garantir que a string foi escrita no arquivo
        } 
        System.out.print(printPuzzle);   

        //escrita das palavras no ficheiro
        String printWords = solver.toStringWords();
        if (WriteinFile != null){
        WriteinFile.write(printWords); // escrever no arquivo em vez de imprimir no console
        WriteinFile.flush(); // garantir que a string foi escrita no arquivo
        WriteinFile.close(); // fechar o arquivo
        }

        System.out.println(printWords);
        System.out.println("");

        // print da informação de todas as palavras
        String printWordsResults = solver.toStringResults();
        System.out.println(printWordsResults);

        // print do puzzle final
        solver.makefinalPuzzle();
        String printFinalPuzzle = solver.toStringFinalPuzzle();
        System.out.println(printFinalPuzzle);

    }

    public static List<String> ReadWordsFromFile(String file) {
        String linha = "";
        List<String> words = new ArrayList<String>();

        try {
            FileReader arq = new FileReader(file);
            BufferedReader lerArq = new BufferedReader(arq);

            while ((linha = lerArq.readLine()) != null) {
                if (linha.isEmpty()) { // Requisito de entrada 5
                    System.out.println("Existem linhas vazias no ficheiro.");
                    System.exit(0); // sai do programa
                }

                for (String s : linha.split("[;, ]")) { // Requisito de entrada 6
                    words.add(s);
                }
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage()); // aqui também podia ser System.out.println("Erro na abertura do arquivo: ");
        }

        return words;
    }

    // public static String toStringFinalWords(List<String> Listwords) {
    //     String words = "";
    //     for (String word : Listwords) {
    //         words += word + "";
    //     }
    //     return words;
    // }
}
