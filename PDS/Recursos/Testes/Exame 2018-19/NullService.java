//Null Object

class NullService implements Servico{
    private String nome = "nao existe";
    private String descricao = "nao existe";
    private double price = 0.0;

    public String nome(){
        return this.nome;
    };
	public String descricao(){
        return this.descricao;
    };
	public double price(){
        return this.price;
    };

    @Override
    public String toString(){
        return "Servico vazio [nome=" + this.nome + ", descricao=" + this.descricao + ", preco=" + this.price + "]";
    }
}