package lab05.V3;

public class Place {
    private final String name;
    private final String address;

    public Place(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
