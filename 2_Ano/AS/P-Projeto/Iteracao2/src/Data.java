public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        if (validarData(dia, mes, ano)) {
            this.dia = dia;
            this.mes = mes;
            this.ano = ano;
        } else {
            System.err.println("Invalid date!");
            System.exit(1);
        }
    }

    public String toString() {
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }

    private boolean validarData(int dia, int mes, int ano) {
        if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || ano < 1) {
            return false;
        }
        if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30){
            return false;
        }
        if (!leapYear(ano)) {
            if (mes == 2 && dia > 28) {
                return false;
            }
        }
        return true;
    }

    private boolean leapYear(int year) {
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
            return true;
        else
            return false;
    }
}
