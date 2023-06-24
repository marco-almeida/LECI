package lab08.ex1.b;

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
}