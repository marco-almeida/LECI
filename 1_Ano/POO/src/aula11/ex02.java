package aula11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.javatuples.Pair;

public class ex02 {
    public static void main(String[] args) {
        Map<String, String> companhias = processAirlines();
        List<Voo> voos = getFlights(companhias);
        System.out.println(flightsTable(voos)); // a
        writeInfopublico(flightsTable(voos)); // b
        System.out.println(averagesTable(getAverages(voos))); // c
        writeCidades(getArrivals(voos)); // d
    }

    public static Map<String, String> processAirlines() {
        Map<String, String> companhias = new HashMap<>();
        try (Scanner myReader = new Scanner(new File("src/aula11/aula11_files/companhias.txt"))) {
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().split("\t");
                companhias.put(line[0], line[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return companhias;
    }

    public static List<Voo> getFlights(Map<String, String> companhias) {
        List<Voo> voos = new ArrayList<>();
        try (Scanner myReader = new Scanner(new File("src/aula11/aula11_files/voos.txt"))) {
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().split("\t");
                companhias.putIfAbsent(line[1].substring(0, 2), "Unknown Airline");
                LocalTime atraso = line.length == 4 ? LocalTime.parse(line[3]) : LocalTime.of(0, 0);
                voos.add(new Voo(LocalTime.parse(line[0]), line[1], companhias.get(line[1].substring(0, 2)), line[2],
                        atraso));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return voos;
    }

    public static String flightsTable(List<Voo> voos) {
        String s = " ";
        StringBuilder stb = new StringBuilder(
                String.format("Hora   Voo%sCompanhia%sOrigem%sAtraso   Obs%n", s.repeat(7),
                        s.repeat(11), s.repeat(16)));
        for (Voo voo : voos) {
            stb.append(String.format("%s%n", voo.toString()));
        }
        return stb.toString();
    }

    public static void writeInfopublico(String str) {
        try (FileWriter myWriter = new FileWriter("src/aula11/Infopublico.txt")) {
            myWriter.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, LocalTime> getAverages(List<Voo> voos) {
        // por cada voo, obter companhia ,adicionar atraso, incrementer ocorrencia
        Map<String, Pair<Integer, Integer>> inf = new HashMap<>();
        for (Voo voo : voos) {
            if (inf.putIfAbsent(voo.getCompanhia(), new Pair<>(voo.getAtraso().toSecondOfDay(), 1)) != null) {
                inf.put(voo.getCompanhia(),
                        new Pair<>(inf.get(voo.getCompanhia()).getValue0() + voo.getAtraso().toSecondOfDay(),
                                1 + inf.get(voo.getCompanhia()).getValue1()));
            }
        }
        // por cada companhia, dividir segundos por ocorrencias -> meter no dicionario
        Map<String, LocalTime> averages = new TreeMap<>();
        for (String companhia : inf.keySet()) {
            averages.put(companhia,
                    LocalTime.ofSecondOfDay(inf.get(companhia).getValue0() / inf.get(companhia).getValue1()));
        }
        return averages;
    }

    public static String averagesTable(Map<String, LocalTime> mapa) {
        StringBuilder stb = new StringBuilder(String.format("Companhia%sAtraso médio%n", " ".repeat(11)));
        for (String companhia : mapa.keySet()) {
            stb.append(String.format("%-20s%s%n", companhia, mapa.get(companhia)));
        }
        return stb.toString();
    }

    public static Map<String, Integer> getArrivals(List<Voo> voos) {
        Map<String, Integer> info = new HashMap<>();
        for (Voo voo : voos) {
            if (info.putIfAbsent(voo.getOrigem(), 1) != null) {
                info.put(voo.getOrigem(), info.get(voo.getOrigem()) + 1);
            }
        }
        // return treemap reverse ordered by values
        return info.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static void writeCidades(Map<String, Integer> mapa) {
        StringBuilder stb = new StringBuilder(String.format("Origem%sVoos%n", " ".repeat(16)));
        for (String origem : mapa.keySet()) {
            stb.append(String.format("%-22s%d%n", origem, mapa.get(origem)));
        }
        try (FileWriter myWriter = new FileWriter("src/aula11/cidades.txt")) {
            myWriter.write(stb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
