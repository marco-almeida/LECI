package lab06.PstEmpresa;

class Client {
    public static void main(String[] args) {
        Employee ee1 = new Employee("Andre Jose", 1, 123);
        Employee ee2 = new Employee("Luis Carlos", 2, 124);
        Database db = new Database();
        db.addEmployee(ee1);
        db.addEmployee(ee2);

        Empregado emp1 = new Empregado("Marco", "Alves", 3, 1233);
        Empregado emp2 = new Empregado("Jorge", "Seabra", 4, 1000);
        Registos rb = new Registos();
        rb.insere(emp1);
        rb.insere(emp2);

        IRegistos company = new RegistosAdapter(rb); // merge
        company.addDatabase(db);

        Empregado e1 = new Empregado("Ze", "Manel", 123, 760.0);
        Empregado e2 = new Empregado("Maria", "Manuela", 112, 866.0);
        Employee e3 = new Employee("Joao Panao", 111, 900.0);

        company.addEmployee(e1);
        company.addEmployee(e2);
        company.addEmployee(e3);
        System.out.println(company.employeeExists(112) ? "112 é empregado" : "112 nao é empregado");
        company.removeEmployee(e2.codigo());
        System.out.println(company.employeeExists(112) ? "112 é empregado" : "112 nao é empregado");

        company.printEmployees();
    }
}
