package lab06.ListaContactos;

import java.util.List;

public interface ContactsStorageInterface {
    List<Contact> loadContacts();

    boolean saveContacts(List<Contact> list);
}
