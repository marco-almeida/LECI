package lab04.T1;

public class Voo {
    private String codigo;  //código que identifica o voo
    private Aviao aviao;    //informação do avião

    private int [][] classe_exec;    //matriz para guardar os assentos da classe executiva
    private int [][] classe_turs;    //matriz para guardar os assentod da classe turistica

    public Voo(String codigo, Aviao aviao) {
        this.codigo = codigo;
        this.aviao = aviao;
        this.classe_exec = new int [aviao.getFilas_e()][aviao.getLugares_fila_e()];
        this.classe_turs = new int [aviao.getFilas_e()][aviao.getLugares_fila_e()];

        for (int lin = 0; lin < classe_exec.length; lin++) {
            for (int col = 0; col < classe_exec[lin].length; col++) {
                classe_exec[lin][col] = 0;
            }
        }

        for (int lin = 0; lin < classe_turs.length; lin++) {
            for (int col = 0; col < classe_turs[lin].length; col++) {
                classe_turs[lin][col] = 0;
            }
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
    }

    public int[][] getClasse_exec() {
        return classe_exec;
    }

    public void setClasse_exec(int[][] classe_exec) {
        this.classe_exec = classe_exec;
    }

    public int[][] getClasse_turs() {
        return classe_turs;
    }

    public void setClasse_turs(int[][] classe_turs) {
        this.classe_turs = classe_turs;
    } 

    

    
}
