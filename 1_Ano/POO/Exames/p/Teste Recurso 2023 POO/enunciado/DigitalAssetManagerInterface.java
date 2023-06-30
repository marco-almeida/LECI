import java.util.List;
import java.util.Random;

public interface DigitalAssetManagerInterface {

    static final Random random = new Random(57);

    public User registerUser(String userName);
    public User getUser(String userName);
    public User createGuestUser();
    public DigitalObject registerDigitalObject(String doi, User owner);
    public DigitalObject registerDigitalObject(String doi, SensitivityLevel sensitivityLevel, User owner);
    public List<String> listRegisteredUsers();
    public List<String> listUserObjects(User u);

    public boolean setObjectSensitivity(String doi, SensitivityLevel sensitivityLevel, User owner);
    public boolean setPublic(String doi, User owner);
    public boolean shareObject(String doi, User owner, User otherUser);
    public DigitalObject getObject(String doi, User user);


    public static String generateFakeDOI() {
        String prefix = "10.0000/fake/10.";
        int numChars = 7; // Length of the random suffix
        
        StringBuilder sb = new StringBuilder();
        
        // Generate random number for the suffix
        sb.append(random.nextInt(10000) + 1000);
        sb.append("/");

        // Generate random alphanumeric characters for the suffix
        for (int i = 0; i < numChars; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            sb.append(randomChar);
        }
        
        return prefix + sb.toString();
    }

}

