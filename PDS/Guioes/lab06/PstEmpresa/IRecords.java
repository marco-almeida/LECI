package lab06.PstEmpresa;

interface IRecords {
    boolean addWorker(Worker w);
    void removeWorker(int emp_code);
    boolean isWorker(int emp_code);
    void printWorkersRecords();
}
