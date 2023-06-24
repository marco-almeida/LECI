package lab08.ex2;

import lab08.ex2.SocialSecurity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;

class Company {

    public static User user;
    private List<Employee> emps = new ArrayList<>();

    public void admitEmployee(Person p, double salary) {
        Employee e = new Employee(p, salary);
        emps.add(e);
        System.out.printf("Employee %s successfully added to company registry.\n", p.getName());
        SocialSecurity ss = SocialSecurity.getInstance();
        ss.regist(p);
        Insurance i = Insurance.getInstance();
        i.regist(p);
        e.createEmployeeCard();

        // if salary is bigger than avg, give parking rights
        OptionalDouble avg = employees().stream().mapToDouble(Employee::getSalary).average();
        if (salary > avg.orElse(0)) {
            Parking pa = Parking.getInstance();
            pa.allow(p);
        }
    }

    public void paySalaries(int month) {
        for (Employee e : emps) {
            BankAccount ba = e.getPerson().getBankAccount();
            ba.deposit(e.getSalary());
        }
    }

    public List<Employee> employees() {
        return Collections.unmodifiableList(emps);
    }
}