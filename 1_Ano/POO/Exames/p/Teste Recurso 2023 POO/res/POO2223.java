package PooRecurso2022_23;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Notes:
// Não altere o código apresentado *** Do not change the code shown
// Deve apenas completar o código de alinea2, onde indicado *** You should only change the code where indicated in alinea2
// Deve comentar o código para garantir que vai executando parcialmente *** You may comment the code to test and execute partially

public class POO2223 {

    static PrintStream out;

    public static void main(String[] args) throws FileNotFoundException {
        out = new PrintStream(new File("POO_2223EPrec.txt"));
        alinea1();
        alinea2();
        out.close();
    }

    // METHOD USED TO PRINT RESULTS TO SCREEN AND FILE
    // DO NOT CHANGE
    private static void print(String s) {
        System.out.println(s);
        out.println(s);
    }

    // DO NOT CHANGE THIS METHOD
    // CREATE THE CLASS DEFINITIONS ACCORDING TO THE DESCRIPTION IN THE EXAM AND THE CODE BELOW
    private static void alinea1() {
        print("\nAlínea 1) ----------------------------------\n");

        //
        // Creating DigitalAssetManager
        DigitalAssetManager assetManager = new DigitalAssetManager();

        //
        // Registered users
        int numRegisteredUsers = 3;
        User[] users = new User[numRegisteredUsers];
        users[0] = assetManager.registerUser("sunnyday12");
        users[1] = assetManager.registerUser("techguru123");
        users[2] = assetManager.registerUser("musiclover87");

        // Error: username already registered
        User u0 = assetManager.registerUser("sunnyday12");

        //
        // Guest users
        int numGuestUsers = 2;
        User[] guestUsers = new User[numGuestUsers];
        guestUsers[0] = assetManager.createGuestUser();
        guestUsers[1] = assetManager.createGuestUser();

        //
        // Register digital objects
        // Object owner is selected in turn from the list of users
        int numObjects = 15;
        String[] objectDOI = new String[numObjects];
        for (int i = 0; i < numObjects; i++) {
            objectDOI[i] = DigitalAssetManagerInterface.generateFakeDOI();
            assetManager.registerDigitalObject(objectDOI[i], users[i % numRegisteredUsers]);
        }

		//
		// Managing and sharing of digital objects
		User u = users[0];

		// this results in error: user does not own object
		assetManager.setObjectSensitivity(objectDOI[5], SensitivityLevel.LOW, u);

		// this results in error: object can not be made public
		assetManager.setPublic(objectDOI[0], u);

		// this results in error: object can not be shared
		assetManager.shareObject(objectDOI[0], u, users[1]);

		// this results in error: guest users can not access non-public objects
		DigitalObject o = assetManager.getObject(objectDOI[0], guestUsers[1]);


		// user changes object sensitivity level and makes it public
		assetManager.setObjectSensitivity(objectDOI[0], SensitivityLevel.LOW, u);
		assetManager.setPublic(objectDOI[0], u);

		// user changes object sensitivity level and shares it
		assetManager.setObjectSensitivity(objectDOI[3], SensitivityLevel.RESTRICTED, u);
		assetManager.shareObject(objectDOI[3], u, users[1]);

		// OK, guest user can access public objects
		o = assetManager.getObject(objectDOI[0], guestUsers[1]);
		print(String.format("User %s accessed digital object %s", guestUsers[1], o));


		// listings
		print("\nRegistered users:");
		assetManager.listRegisteredUsers().forEach(s->print("  * " + s));

		print(String.format("\nDigital objects of user %s:", u));
		assetManager.listUserObjects(u).forEach(s->print("  * " + s));

    }

    // CHANGE THIS METHOD ONLY WHERE INDICATED
    private static void alinea2() {
        print("\n\nAlínea 2) ----------------------------------\n");

        DigitalAssetManager assetManager = new DigitalAssetManager();

        int numRegisteredUsers = 3;
        User[] users = new User[numRegisteredUsers];
        users[0] = assetManager.registerUser("sunnyday12");
        users[1] = assetManager.registerUser("techguru123");
        users[2] = assetManager.registerUser("musiclover87");

        //
        // Adicione a seguir o código necessário para a leitura do ficheiro e registo dos alugueres.
        // Add the code to read from file and register the bookings.
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("PooRecurso2022_23/digital_objects.txt"));
            lines.removeIf(x -> x.startsWith("#"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String s : lines) {
            String[] data = s.split(";");
            String doi = data[0];
            String userName = data[1];
            User u = assetManager.getUser(userName);
            if (u == null) {
                System.out.printf("** Username %s not found.\n", userName);
            }
            SensitivityLevel sensitivityLevel = SensitivityLevel.valueOf(data[2]);
            assetManager.registerDigitalObject(doi, sensitivityLevel, u);
        }

        // Não modifique o metodo a partir daqui. Pode comentar para executar o programa.
        // Do not modify the method from this point on. You may comment to excute the programme.
        //

        print("\nFinished reading file.\n");

        assetManager.setPublic("10.0000/fake/10.8213/flotbeo", users[1]);

        assetManager.setObjectSensitivity("10.0000/fake/10.1166/nmqkyjb", SensitivityLevel.RESTRICTED, users[0]);
        assetManager.shareObject("10.0000/fake/10.1166/nmqkyjb", users[0], users[1]);
        assetManager.shareObject("10.0000/fake/10.1166/nmqkyjb", users[0], users[2]);
        assetManager.shareObject("10.0000/fake/10.10213/ykbojmc", users[0], users[2]);
        assetManager.shareObject("10.0000/fake/10.4693/auyejmr", users[1], users[2]);
        assetManager.shareObject("10.0000/fake/10.10154/knnweak", users[1], users[2]);

        // listar utilizadores com acesso a um objeto
        String doi = "10.0000/fake/10.1166/nmqkyjb";
        print(String.format("\nUsers with access to digital object %s", doi));
        assetManager.listUsersWithObjectAccess(doi).stream().forEach(s -> print("  * " + s));

        // listar objetos partilhados com um utilizador
        print(String.format("\nObjects shared with user %s", users[2]));
        assetManager.listObjectSharedWithUser(users[2]).stream().forEach(s -> print("  * " + s));

        print("\n");
    }

}
