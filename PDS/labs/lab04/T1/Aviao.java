package lab04.T1;

public class Aviao {
    private int filas_t;            //numero de filas da classe turistica
    private int lugares_fila_t;     //numero de lugares por fila na classe turistica
    private int lugaresTuristica;   //númeor de lugares na classe turistica

    private int filas_e;            //numero de filas da classe executiva
    private int lugares_fila_e;     //numero de lugares por fila na classe executiva
    private int lugaresExecutiva;   //númeor de lugares na classe executiva

    private int num_lugar_avião;    //número de lugares do avião 


    //construtor para avião com classe exectiva
    public Aviao( int filas_e, int lugares_fila_e, int filas_t, int lugares_fila_t) {
        this.filas_t = filas_t;
        this.lugares_fila_t = lugares_fila_t;
        this.filas_e = filas_e;
        this.lugares_fila_e = lugares_fila_e;
    }

    //construtor se não houver classe executiva
    public Aviao(int filas_t, int lugares_fila_t) {
        this.filas_t = filas_t;
        this.lugares_fila_t = lugares_fila_t;
        this.filas_e = 0;
        this.lugares_fila_e = 0;
    }

    public int getFilas_t() {
        return filas_t;
    }

    public void setFilas_t(int filas_t) {
        this.filas_t = filas_t;
    }

    public int getLugares_fila_t() {
        return lugares_fila_t;
    }

    public void setLugares_fila_t(int lugares_fila_t) {
        this.lugares_fila_t = lugares_fila_t;
    }

    public int getLugaresTuristica() {
        return lugaresTuristica;
    }

    public void setLugaresTuristica(int lugaresTuristica) {
        this.lugaresTuristica = this.filas_t * this.lugares_fila_t;
    }

    public int getFilas_e() {
        return filas_e;
    }

    public void setFilas_e(int filas_e) {
        this.filas_e = filas_e;
    }

    public int getLugares_fila_e() {
        return lugares_fila_e;
    }

    public void setLugares_fila_e(int lugares_fila_e) {
        this.lugares_fila_e = lugares_fila_e;
    }

    public int getLugaresExecutiva() {
        return lugaresExecutiva;
    }

    public void setLugaresExecutiva(int lugaresExecutiva) {
        this.lugaresExecutiva = this.filas_e * this.lugares_fila_e;
    }

    public int getNum_lugar_avião() {
        return num_lugar_avião;
    }

    public void setNum_lugar_avião(int num_lugar_avião) {
        this.num_lugar_avião = this.lugaresExecutiva + this.lugaresTuristica;
    } 
    
}
