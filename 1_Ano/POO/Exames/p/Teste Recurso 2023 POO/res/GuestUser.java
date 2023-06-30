package PooRecurso2022_23;

public class GuestUser extends User {

    private static int numIds = -1;

    public GuestUser(String name) {
        super(name);
        setId(numIds--);
    }
}
