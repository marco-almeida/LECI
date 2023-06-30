package PooRecurso2022_23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DigitalAssetManager implements DigitalAssetManagerInterface {
    private final Map<String, User> users;
    private final Map<String, DigitalObject> objects;
    private final Map<User, List<DigitalObject>> sharedObjects;

    public DigitalAssetManager() {
        users = new HashMap<>();
        objects = new HashMap<>();
        sharedObjects = new HashMap<>();
    }

    @Override
    public User registerUser(String userName) {
        User u = null;
        if (users.containsKey(userName)) {
            System.out.println("That username already exists.");
        } else {
            u = new RegisteredUser(userName);
            users.put(userName, u);
        }
        return u;
    }

    @Override
    public User getUser(String userName) {
        return users.get(userName);
    }

    @Override
    public User createGuestUser() {
        String generatedUserName;
        do {
            generatedUserName = DigitalAssetManagerInterface.generateGuestName();
        } while (users.containsKey(generatedUserName));
        User u = new GuestUser(generatedUserName);
        users.put(generatedUserName, u);
        return u;
    }

    @Override
    public DigitalObject registerDigitalObject(String doi, User owner) {
        return registerDigitalObject(doi, SensitivityLevel.PERSONAL, owner);
    }

    @Override
    public DigitalObject registerDigitalObject(String doi, SensitivityLevel sensitivityLevel, User owner) {
        if (owner != null && owner.getClass() != RegisteredUser.class) {
            System.out.println("Only registered users can be digital objects' owners");
            return null;
        }
        DigitalObject dobj = new DigitalObject(doi, owner, sensitivityLevel);
        objects.put(doi, dobj);
        return dobj;
    }

    @Override
    public List<String> listRegisteredUsers() {
        List<String> registeredUsers = new ArrayList<>();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            User u = entry.getValue();
            if (u instanceof RegisteredUser) {
                registeredUsers.add(u.toString());
            }
        }
        return registeredUsers;
    }

    @Override
    public List<String> listUserObjects(User u) {
        List<String> userObjects = new ArrayList<>();
        for (Map.Entry<String, DigitalObject> entry : objects.entrySet()) {
            DigitalObject dobj = entry.getValue();
            if (u.equals(dobj.getOwner())) {
                userObjects.add(dobj.toString());
            }
        }
        return userObjects;
    }

    @Override
    public boolean setObjectSensitivity(String doi, SensitivityLevel sensitivityLevel, User owner) {
        DigitalObject dobj = objects.get(doi);
        if (!owner.equals(dobj.getOwner())) {
            System.out.println("User does not own the digital object.");
            return false;
        }
        dobj.setSensitivity(sensitivityLevel);
        return true;
    }

    @Override
    public boolean setPublic(String doi, User owner) {
        DigitalObject dobj = objects.get(doi);
        if (dobj.getSensitivity() == SensitivityLevel.LOW) {
            dobj.setPublic(true);
            return true;
        }
        System.out.println("Only digital objects with sensitivity level LOW can be made public.");
        return false;
    }

    @Override
    public boolean shareObject(String doi, User owner, User otherUser) {
        DigitalObject dobj = objects.get(doi);
        if (dobj.getSensitivity() == SensitivityLevel.PERSONAL) {
            System.out.println("Digital objects with sensitivity level PERSONAL cannot be shared.");
            return false;
        }
        sharedObjects.computeIfAbsent(otherUser, k -> new ArrayList<>()).add(dobj);
        return true;
    }

    @Override
    public DigitalObject getObject(String doi, User user) {
        DigitalObject dobj = objects.get(doi);
        if (!dobj.isPublic() && user instanceof GuestUser) {
            System.out.println("Guest users can only access public digital objects.");
            return null;
        }
        return dobj;
    }

    private boolean userHasObjectAccess(User user, DigitalObject dobj) {
        List<User> sharedUsers = new ArrayList<>();
        for (Map.Entry<User, List<DigitalObject>> entry : sharedObjects.entrySet()) {
            User u = entry.getKey();
            List<DigitalObject> value = entry.getValue();
            if (value.contains(dobj)) {
                sharedUsers.add(u);
            }
        }
        if (dobj.getOwner().equals(user) || dobj.isPublic() || sharedUsers.contains(user)) {
            return true;
        }

        return dobj.getSensitivity() == SensitivityLevel.RESTRICTED && user instanceof RegisteredUser;
    }

    @Override
    public List<String> listUsersWithObjectAccess(String doi) {
        List<String> finalList = new ArrayList<>();
        DigitalObject dobj = objects.get(doi);
        List<User> allUsers = users.values().stream().toList();
        for (User u : allUsers) {
            if (userHasObjectAccess(u, dobj) && dobj.getOwner().equals(u)) {
                finalList.add(u + " - Owner");
            } else if (userHasObjectAccess(u, dobj)) {
                finalList.add(u.toString());
            }
        }
        return finalList;
    }

    @Override
    public List<DigitalObject> listObjectSharedWithUser(User u) {
        List<DigitalObject> result = new ArrayList<>();
        if (sharedObjects.containsKey(u)) {
            result.addAll(sharedObjects.get(u));
        }
        return result;
    }
}
