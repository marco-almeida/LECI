class Net implements Servico{
    private String nome;
    private String descricao;
    private double price;
    private String speed; //Internet speed

    Net(String nome, String descricao, double price, String speed){
        this.nome = nome;
        this.descricao = descricao;
        this.price = price;
        this.speed = speed;
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
    public double speed(){
        return this.speed();
    };

    @Override
    public String toString(){
        return "TV [nome=" + this.nome + ", descricao=" + this.descricao + ", preco=" + this.price + "],speed="+ this.speed;
    }
}