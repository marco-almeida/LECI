package lab06.ListaContactos;

public interface ContactsInterface {
    void openAndLoad(ContactsStorageInterface store);

    void saveAndClose();

    void saveAndClose(ContactsStorageInterface store);

    boolean exist(Contact contact);

    Contact getByName(String name);

    boolean add(Contact contact);

    boolean remove(Contact contact);
}
