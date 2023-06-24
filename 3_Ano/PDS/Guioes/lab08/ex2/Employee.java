package lab08.ex2;

class Employee {
    private double salary;
    private Person person;

    public Employee(Person person, double s) {
        this.person = person;
        salary = s;
    }


    public double getSalary() {
        return salary;
    }

    public Person getPerson() {
        return this.person;
    }

    public void createEmployeeCard() {
        System.out.printf("Created employee card for employee %s\n", person.getName());
    }
}