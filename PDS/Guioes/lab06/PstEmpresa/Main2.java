package lab06.PstEmpresa;

public class Main2 {
    public static void main(String[] args) {
        Worker e1 = new Worker("Ze Manel", 123, 760.0);
        Worker e2 = new Worker("Maria Manuela", 112, 866.0);
        Worker e3 = new Worker("Joao Panao", 111, 900.0);

        Records records = new Records();
        records.addWorker(e1);
        records.addWorker(e2);
        records.addWorker(e3);
        System.out.println(records.isWorker(112) ? "112 é empregado" : "112 nao é empregado");
        records.removeWorker(e2.getCode());
        System.out.println(records.isWorker(112) ? "112 é empregado" : "112 nao é empregado");

        records.printWorkersRecords();
    }
}
