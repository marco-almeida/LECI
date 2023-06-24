package lab03.Voos;

import java.util.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FlightManager implements Manager {

    private final Map<String, Flight> flights = new HashMap<>();

    public boolean loadFlightFile(String filePath) {
        List<String> fileContent;
        // read file
        try {
            fileContent = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Failed to read file " + filePath);
            return false;
        }
        // from file, create a flight object
        Flight flight = getFlightInFile(fileContent);
        if (flight == null) {
            System.out.println("Invalid file format");
            return false;
        }
        flights.put(flight.getCode(), flight);
        return true;
    }

    public String flightReservationsMap(String flightCode) {
        if (flights.get(flightCode) != null) {
            return flights.get(flightCode).reservationsMap();
        }
        return "Flight not found";
    }

    private Flight getFlightInFile(List<String> fileContent) {
        if (fileContent.get(0).charAt(0) != '>') {
            return null;
        }
        // get flight information
        String[] flightInfo = fileContent.get(0).split(" ");
        // flight code
        String flightCode = flightInfo[0].substring(1);
        if (flights.containsKey(flightCode)) {
            System.out.println("Flight already exists");
            return null;
        }
        // number of rows and seats per row in executive class
        int touristRows;
        int touristRowsSeats;
        int executiveRows = 0;
        int executiveRowsSeats = 0;
        try {
            if (flightInfo.length >= 3) {
                executiveRows = Integer.parseInt(flightInfo[1].split("x")[0]);
                executiveRowsSeats = Integer.parseInt(flightInfo[1].split("x")[1]);
                touristRows = Integer.parseInt(flightInfo[2].split("x")[0]);
                touristRowsSeats = Integer.parseInt(flightInfo[2].split("x")[1]);
            } else {
                touristRows = Integer.parseInt(flightInfo[1].split("x")[0]);
                touristRowsSeats = Integer.parseInt(flightInfo[1].split("x")[1]);
            }
        } catch (Exception e) {
            return null;
        }
        Flight flight = new Flight(flightCode, executiveRows, executiveRowsSeats, touristRows, touristRowsSeats);
        // print initial state
        System.out.println(flight);
        // load any initial reservations in file
        loadReservations(flight, fileContent.subList(1, fileContent.size()));
        return flight;
    }

    private void loadReservations(Flight flight, List<String> reservations) {
        for (String reservation : reservations) {
            String[] reservationInfo = reservation.split(" ");
            // reservation class
            char reservationClass = reservationInfo[0].charAt(0);
            try {
                int seats = Integer.parseInt(reservationInfo[1]);
                flight.addReservation(reservationClass, seats);
            } catch (Exception e) {
                System.out.println("Erro a fazer reserva de " + Arrays.toString(reservationInfo));
                return;
            }
        }
    }

    @Override
    public void addFlight(String command) {
        String newCommand = ">" + command;
        Flight flight = getFlightInFile(Collections.singletonList(newCommand));
        if (flight == null) {
            System.out.println("Invalid command");
        } else if (flights.putIfAbsent(flight.getCode(), flight) != null) {
            System.out.println("Flight already exists");
        }
    }

    @Override
    public String addReservation(String command) {
        String[] commands = command.split(" ");
        String flightCode = commands[0];
        String type = commands[1];
        int seats;
        try {
            seats = Integer.parseInt(commands[2]);
        } catch (Exception e) {
            return "Invalid command";
        }

        if (flights.get(flightCode) != null) {
            List<String> allocSeats = flights.get(flightCode).addReservation(type.charAt(0), seats);
            if (allocSeats.size() == 0) {
                return "";
            }
            StringBuilder stb = new StringBuilder(String.format("%s:%d = %s", flightCode,
                    flights.get(flightCode).getReservations() - 1, allocSeats.get(0)));
            for (int i = 1; i < allocSeats.size(); i++) {
                stb.append(" | ").append(allocSeats.get(i));
            }
            return stb.toString();
        }
        return "Flight not found";
    }

    @Override
    public void cancelReservation(String command) {
        String[] commands = command.split(":");
        String flightCode = commands[0];
        int seats;
        try {
            seats = Integer.parseInt(commands[1]);
        } catch (Exception e) {
            System.out.println("Invalid command");
            return;
        }

        if (flights.get(flightCode) != null) {
            flights.get(flightCode).cancelReservation(seats);
        } else {
            System.out.println("Flight not found");
        }
    }

}
