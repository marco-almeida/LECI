package lab06.PstEmpresa;

interface IRegistos {
    void addEmployee(Empregado e);
    void addEmployee(Employee e);

    void removeEmployee(long empNumber);

    boolean employeeExists(long empNumber);

    void printEmployees();

    void addDatabase(Database d);

}
