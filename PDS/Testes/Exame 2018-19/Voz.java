class Voz implements Servico{
    private String nome;
    private String descricao;
    private double price;

    Voz(String nome, String descricao, double price){
        this.nome = nome;
        this.descricao = descricao;
        this.price = price;
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

    @Override
    public String toString(){
        return "Voz [nome=" + this.nome + ", descricao=" + this.descricao + ", preco=" + this.price + "]";
    }
}