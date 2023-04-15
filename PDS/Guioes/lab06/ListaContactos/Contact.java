package lab06.ListaContactos;

import java.util.Objects;

public class Contact {
    private String email;
    private Long number;
    private String name;

    public Contact(String name, Long number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public boolean hasEmail() {
        return email != null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", number=" + number + ", email=" + email + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(email, contact.email) && Objects.equals(number, contact.number) && Objects.equals(name, contact.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, number, name);
    }
}