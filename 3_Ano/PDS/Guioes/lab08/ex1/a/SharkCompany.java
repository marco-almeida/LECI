package lab08.ex1.a;

import java.util.List;

public class SharkCompany {
    public static void main(String[] args) {

        Company shark = new Company();
        Company.user = User.COMPANY;
        shark.admitPerson("Maria Silva", 1000);
        shark.admitPerson("Manuel Pereira", 900);
        shark.admitPerson("Aurora Machado", 1200);
        shark.admitPerson("Augusto Lima", 1100);
        List<Employee> sharkEmps = shark.employees();

        for (Employee e : sharkEmps)
            // "talking to strangers", but this is not a normal case
            System.out.println(e.getBankAccount().balance());
        shark.paySalaries(1);
        for (Employee e : sharkEmps) {
            e.getBankAccount().withdraw(500);
            System.out.println(e.getBankAccount().balance());
        }
    }
}