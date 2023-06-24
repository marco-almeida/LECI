package lab06.PstEmpresa;

public class RegistosAdapter implements IRegistos {
    private Registos adaptee;

    public RegistosAdapter(Registos adaptee) {
        this.adaptee = adaptee;
    }

    public void addDatabase(Database d) {
        for (Employee e : d.getAllEmployees()) {
            adaptee.insere(employeeToEmpregado(e));
        }
    }

    public Empregado employeeToEmpregado(Employee e) {
        return new Empregado(e.getName().split(" ")[0], e.getName().split(" ")[1], (int) e.getEmpNum(), e.getSalary());
    }

    @Override
    public void addEmployee(Empregado e) {
        adaptee.insere(e);
    }

    @Override
    public void addEmployee(Employee e) {
        addEmployee(employeeToEmpregado(e));
    }

    @Override
    public void removeEmployee(long empNumber) {
        adaptee.remove((int) empNumber);
    }

    @Override
    public boolean employeeExists(long empNumber) {
        return adaptee.isEmpregado((int) empNumber);
    }

    @Override
    public void printEmployees() {
        // sort list by employee code
        adaptee.listaDeEmpregados().sort((x, y) -> x.codigo() - y.codigo());

        adaptee.listaDeEmpregados().forEach(x -> System.out.printf("%s %s No. %d %.1f\n", x.nome(), x.apelido(), x.codigo(), x.salario()));
    }
}
