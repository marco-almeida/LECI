package lab06.PstEmpresa;

class Records implements IRecords {
    private Database database;
    private Registos registos;

    public Records () {
        database = new Database();
        registos = new Registos();
    }

    @Override
    public boolean addWorker(Worker w) {
        registos.insere(new Empregado(w.getName(), null, w.getCode(), w.getSalary()));
        return database.addEmployee(new Employee(w.getName(), w.getCode(), w.getSalary()));
    }

    @Override
    public void removeWorker(int emp_code) {
        registos.remove(emp_code);
        database.deleteEmployee(emp_code);
    }

    @Override
    public boolean isWorker(int emp_code) {
        return registos.isEmpregado(emp_code);
    }

    @Override
    public void printWorkersRecords() {
        for (Empregado e : registos.listaDeEmpregados()) {
            System.out.printf("Worker %s %d Salary: %.2f\n", e.nome(), e.codigo(), e.salario());
        }
    }
}
