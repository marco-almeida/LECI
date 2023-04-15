package lab06.PstEmpresa;

import java.util.List;

public class Ex1 {
    public static void main(String[] args) {
        System.out.println("---------------Sweets---------------");
        Employee e1 = new Employee("Ze Manel", 123L, 760.0);
        Employee e2 = new Employee("Maria Manuela", 112L, 866.0);
        Employee e3 = new Employee("Joao Panao", 111L, 900.0);
        Database db = new Database();
        db.addEmployee(e1);
        db.addEmployee(e2);
        db.addEmployee(e3);
        db.deleteEmployee(112);
        Employee[] employees = db.getAllEmployees();
        for (Employee e : employees) {
            System.out.printf("%s %d %f\n", e.getName(), e.getEmpNum(), e.getSalary());
        }
        System.out.println("---------------Petiscos---------------");
        Empregado e4 = new Empregado("Ze", "Manel", 123, 760.0);
        Empregado e5 = new Empregado("Maria", "Manuela", 112, 866.0);
        Empregado e6 = new Empregado("Joao", "Panao", 111, 900.0);
        Registos db2 = new Registos();
        db2.insere(e4);
        db2.insere(e5);
        db2.insere(e6);
        System.out.println(db2.isEmpregado(112) ? "112 é empregado" : "112 nao é empregado");
        db2.remove(112);
        System.out.println(db2.isEmpregado(112) ? "112 é empregado" : "112 nao é empregado");
        List<Empregado> empregados = db2.listaDeEmpregados();

        for (Empregado e : empregados) {
            System.out.printf("%s %s %d %f\n", e.nome(), e.apelido(), e.codigo(), e.salario());
            System.out.println(db2.isEmpregado((e.codigo())) ? "é empregado" : "nao é empregado");
        }
    }
}