package aula12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ex02 {
    public static void main(String[] args) {
        List<Movie> movies = readFile();
        movies.sort(Comparator.comparing(Movie::getName));
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        System.out.println("");
        movies.sort(Comparator.comparing(Movie::getScore).reversed());
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        movies.sort(Comparator.comparing(Movie::getRunningTime));
        System.out.println("");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        Set<String> generos = new HashSet<>();
        for (Movie movie : movies) {
            generos.add(movie.getGenre());
        }
        System.out.println("\n" + generos);
        writeFile(movies);
    }

    public static List<Movie> readFile() {
        List<Movie> movies = new ArrayList<>();
        try (Scanner myReader = new Scanner(new File("src/aula12/aula12_files/movies.txt"))) {
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String[] atributes = myReader.nextLine().split("\t");
                movies.add(new Movie(atributes[0], Double.parseDouble(atributes[1]), atributes[2], atributes[3],
                        Integer.parseInt(atributes[4])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static void writeFile(List<Movie> movies) {
        StringBuilder stb = new StringBuilder();
        for (Movie movie : movies) {
            if (movie.getGenre().equals("comedy") && movie.getScore() > 60) {
                stb.append(movie.toString() + "\n");
            }
        }
        try (FileWriter myWriter = new FileWriter("src/aula12/myselection.txt")) {
            myWriter.write(stb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
