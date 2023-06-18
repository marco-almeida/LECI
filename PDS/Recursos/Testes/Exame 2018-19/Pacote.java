//Pattern: Compound
import java.util.List;
import java.util.ArrayList;

class Pacote implements Servico{
    private String nome;
    private String descricao;
    private double price;

    private int months; //Months of adherance
    private List<Servico> servicos; //Services in the package

    Pacote(String nome, String descricao, int months){
        this.nome = nome;
        this.descricao = descricao;
        this.price = 0;

        this.months = months;
        this.servicos = new ArrayList<>();
    }

    public String nome(){
        return this.nome;
    };
	public String descricao(){
        return this.descricao;
    };
	public double price(){
        return this.price;
    };

    //Checks if package offers specific service
    public boolean hasService(Servico serv){
        if (this.servicos.contains(serv)){
            return true;
        }else{
            return false;
        }
    }

    //Adds a service to the package (granted it's not a duplicate)
    public boolean add(Servico serv){

        if(!hasService(serv)){
            this.servicos.add(serv);
            this.price += serv.price()*0.75; //Add price of service * 0.75 (gets a 25% discount)
            return true;
        }

        return false;
        
    }

    //Prints all services in package
    public String getServices(StringBuilder st){
        for(Servico i : this.servicos){ //Print elements
            st.append("\n   "+i.toString());
        }

        return st.toString();
    }

    @Override
    public String toString(){
        StringBuilder st = new StringBuilder();
        st.append("Pacote [nome=" + this.nome + ", descricao=" + this.descricao + ", preco=" + this.price + "] com fidelizacao de " + this.months + " meses");
        
        getServices(st);

        return st.toString();
    }
}