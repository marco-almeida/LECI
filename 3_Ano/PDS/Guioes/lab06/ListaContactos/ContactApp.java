package lab06.ListaContactos;

import java.util.ArrayList;
import java.util.List;

public class ContactApp implements ContactsInterface {

    private ContactsStorageInterface storage;
    private List<Contact> contacts = new ArrayList<>();

    @Override
    public void openAndLoad(ContactsStorageInterface store) {
        this.storage = store;
        contacts = store.loadContacts();
    }

    @Override
    public void saveAndClose() {
        storage.saveContacts(contacts);
    }

    @Override
    public void saveAndClose(ContactsStorageInterface store) {
        store.saveContacts(contacts);
    }

    @Override
    public boolean exist(Contact contact) {
        return contacts.stream().anyMatch(x -> x.equals(contact));
    }

    @Override
    public Contact getByName(String name) {
        return contacts.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public boolean add(Contact contact) {
        return contacts.add(contact);
    }

    @Override
    public boolean remove(Contact contact) {
        return contacts.remove(contact);
    }
}
