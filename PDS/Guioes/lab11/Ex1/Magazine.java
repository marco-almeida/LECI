package lab11.Ex1;

public class Magazine {
    private SortStrategy strat;
    private Cellphone[] listOfCellphones;

    public Magazine(SortStrategy strat){
        this.strat = strat;
        listOfCellphones = fillList();
    }

    public void sort(){
        strat.sort();
    }

    public void setStrategy(SortStrategy strat){
        this.strat = strat;
    }

    public void getList(){
        System.out.println(listOfCellphones);
    }

    public Cellphone[] fillList(){
        Cellphone[] list = new Cellphone[5];
        Cellphone c1 = new Cellphone("p1", 100, 2, "c1");
        Cellphone c2 = new Cellphone("p2", 200, 2, "c2");
        Cellphone c3 = new Cellphone("p3", 300, 2, "c3");
        Cellphone c4 = new Cellphone("p4", 400, 2, "c4");
        Cellphone c5 = new Cellphone("p5", 500, 2, "c5");

        list[0] = c1;
        list[1] = c2;
        list[2] = c3;
        list[3] = c4;
        list[4] = c5;

        return list;
    }
}

