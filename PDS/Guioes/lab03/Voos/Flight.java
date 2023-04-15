package lab03.Voos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Flight {
    private final String code;
    private final Map<Character, char[][]> allSeats = new HashMap<>();
    private final Map<Character, Integer> availableSeats = new HashMap<>();
    private int reservations = 1;

    public Flight(String code, int executiveRows, int executiveRowsSeats, int touristRows, int touristRowsSeats) {
        this.code = code;
        allSeats.put('T', new char[touristRows][touristRowsSeats]);
        allSeats.put('E', new char[executiveRows][executiveRowsSeats]);
        availableSeats.put('T', touristRows * touristRowsSeats);
        availableSeats.put('E', executiveRows * executiveRowsSeats);
    }

    public List<String> addReservation(char seatType, int seats) {
        if (!availableSeats(seatType, seats))
            return List.of();

        List<String> allocatedSeats = new ArrayList<>();

        // first, try to fill an empty row
        int emptyRow = getEmptyRow(seatType);
        if (emptyRow != -1 && seats <= allSeats.get(seatType)[emptyRow].length) {
            // if there is an empty row and there are enough seats for the whole reservation
            // in that row
            char[][] existingArray = allSeats.get(seatType);
            int seatsLeft = seats;
            for (int i = 0; i < allSeats.get(seatType)[emptyRow].length && seatsLeft > 0; i++, seatsLeft--) {
                existingArray[emptyRow][i] = (char) reservations;
                allocatedSeats.add(String.format("%d%c",
                        emptyRow + 1 + allSeats.getOrDefault('E', new char[0][0]).length, (char) (i + 65)));
            }
            allSeats.put(seatType, existingArray);
            // update available seats
            availableSeats.put(seatType, availableSeats.get(seatType) - seats);
            reservations++;
            return allocatedSeats;
        }
        // if empty rows cant be filled, iterate over all seats and fill them
        return fillSeperateSeats(seatType, seats, emptyRow, allocatedSeats);
    }

    private List<String> fillSeperateSeats(char seatType, int seats, int startingPoint, List<String> allocatedSeats) {
        startingPoint = startingPoint == -1 ? 0 : startingPoint;
        int seatsLeft = seats;
        char[][] existingArray = allSeats.get(seatType);
        for (int i = startingPoint; seatsLeft > 0; i++) {
            // loop back to the beginning of the array if we reach the end
            // and there are still seats left
            if (i >= existingArray.length) {
                i = 0;
            }
            for (int j = 0; j < existingArray[i].length && seatsLeft > 0; j++) {
                if (existingArray[i][j] == 0) {
                    allocatedSeats.add(String.format("%d%c", i + 1 + allSeats.getOrDefault('E', new char[0][0]).length,
                            (char) (j + 65)));
                    existingArray[i][j] = (char) reservations;
                    seatsLeft--;
                }
            }
        }
        allSeats.put(seatType, existingArray);
        availableSeats.put(seatType, availableSeats.get(seatType) - seats);
        reservations++;
        return allocatedSeats;
    }

    private boolean availableSeats(char seatType, int seats) {
        if (seatType == 'E' && allSeats.get('E').length == 0)
            System.out.println("Classe executiva não disponível neste voo.");

        if (seats > availableSeats.get(seatType)) {
            System.out.println("Não foi possível obter lugares para a reserva: " + seatType + " " + seats);
            return false;
        }
        return true;
    }

    private int getEmptyRow(char type) {
        // whole row must be fully empty
        for (int i = 0; i < allSeats.get(type).length; i++) {
            if (Arrays.equals(allSeats.get(type)[i], new char[allSeats.get(type)[i].length])) {
                return i;
            }
        }
        return -1;
    }

    public String reservationsMap() {
        StringBuilder stb = new StringBuilder("\n  ");
        // print row numbers
        for (int i = 1; i <= allSeats.get('T').length + allSeats.get('E').length; i++) {
            stb.append(String.format("%2d ", i));
        }

        int maxLen = allSeats.get('T')[0].length;
        if (allSeats.get('E').length > 0) {
            maxLen = Math.max(allSeats.get('E')[0].length, allSeats.get('T')[0].length);
        }

        int it = 0;
        for (char line = 'A'; line < maxLen + 'A'; line++, it++) {
            stb.append(String.format("%n%c ", line));
            // print empty seats if row is not of same size between classes
            if (allSeats.get('E').length > 0 && (it >= allSeats.get('E')[0].length)) {
                stb.append("   ".repeat(allSeats.get('E').length));
            }
            // print reservation numbers for each ROW (vertically)
            stb.append(getLineReservationMap('E', line));
            stb.append(getLineReservationMap('T', line));
        }
        return stb.toString();
    }

    private String getLineReservationMap(char type, int line) {
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < allSeats.get(type).length; i++) {
            for (int j = 0; j < allSeats.get(type)[i].length; j++) {
                if (line - 'A' == j) {
                    stb.append(String.format("%2d ", (int) allSeats.get(type)[i][j]));
                }
            }
        }
        return stb.toString();
    }

    public void cancelReservation(int reservationNumber) {
        for (Entry<Character, char[][]> entry : allSeats.entrySet()) {
            for (int i = 0; i < entry.getValue().length; i++) {
                for (int j = 0; j < entry.getValue()[i].length; j++) {
                    if (entry.getValue()[i][j] == reservationNumber) {
                        entry.getValue()[i][j] = 0;
                        availableSeats.put(entry.getKey(), availableSeats.get(entry.getKey()) + 1);
                    }
                }
            }
        }
    }

    public String toString() {
        StringBuilder stb = new StringBuilder(String.format("Código de voo %s. Lugares disponíveis: ", code));
        if (allSeats.get('E').length != 0) {
            stb.append(String.format("%d lugares em classe Executiva; ", availableSeats.get('E')));
        }
        stb.append(String.format("%d lugares em classe Turística.", availableSeats.get('T')));
        return stb.toString();
    }

    public String getCode() {
        return code;
    }

    public int getReservations() {
        return reservations;
    }
}
