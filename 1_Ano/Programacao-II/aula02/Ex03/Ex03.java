package Ex03;
import Ex03.pt.ua.prog2.Contacto2;

public class Ex03 {
    public static void main(String[] args) {
        Contacto2[] cl = new Contacto2[4];
        cl[0] = new Contacto2("Ana", "912344566");
        cl[1] = new Contacto2("Rita", "867367834", "rita@gmail.com");
        cl[2] = new Contacto2("Paulo", "897476388", "paulo@hotmail.com");
        cl[3] = new Contacto2("Carlos", "674767867");

        for (int i = 0; i < cl.length; i++) {
            System.out.println(cl[i].nome() + ": " + cl[i].telefone() + "; " + cl[i].eMail());
        }
    }
}
