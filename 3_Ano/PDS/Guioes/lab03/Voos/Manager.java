package lab03.Voos;

public interface Manager {
    boolean loadFlightFile(String filePath);
    String flightReservationsMap(String command);
    void addFlight(String command);
    String addReservation(String command);
    void cancelReservation(String command);
}
