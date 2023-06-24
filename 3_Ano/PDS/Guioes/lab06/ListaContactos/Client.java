package lab06.ListaContactos;

class Client {
    public static void main(String[] args) {
        ContactsStorageInterface storageTxt = new TXTStorage("lab06\\ListaContactos\\files\\contacts.txt");
        ContactsInterface txtContacts = new ContactApp();
        txtContacts.openAndLoad(storageTxt);
        System.out.println(txtContacts.getByName("Maria Manuela"));
        Contact c4 = new Contact("Novo 4 no Txt", 444444444L, "4@ua.pt");
        System.out.println("Adding " + c4);
        txtContacts.add(c4);
        System.out.println("4@ua.pt exists: " + txtContacts.exist(c4));
        txtContacts.remove(txtContacts.getByName(c4.getName()));
        txtContacts.saveAndClose();

        ContactsStorageInterface storageCsv = new CSVStorage("lab06\\ListaContactos\\files\\contacts.csv");
        ContactsInterface csvContacts = new ContactApp();
        csvContacts.openAndLoad(storageCsv);
        System.out.println(csvContacts.getByName("Maria Manuela"));
        Contact c5 = new Contact("Novo 5 no Csv", 555555555L, "5@ua.pt");
        System.out.println("Adding " + c5);
        csvContacts.add(c5);
        System.out.println("5@ua.pt exists: " + csvContacts.exist(c5));
        csvContacts.remove(csvContacts.getByName(c5.getName()));
        csvContacts.saveAndClose();
    }
}
