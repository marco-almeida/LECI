package PooRecurso2022_23;

public class RegisteredUser extends User {
    private static int numIds = 0;

    public RegisteredUser(String name) {
        super(name);
        setId(numIds++);
    }
}
