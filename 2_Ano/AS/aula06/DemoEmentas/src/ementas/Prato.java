package ementas;

import java.util.ArrayList;
import java.util.List;

public class Prato implements Comparable<Prato> {

    private String nome;
    private List<Alimento> listaAlimentos;
    private int contagemIngredientes;

    private double preco;

    public Prato(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
        listaAlimentos = new ArrayList<>();
        contagemIngredientes = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Prato [nome=" + nome + "," + contagemIngredientes + " ingredientes, preco " + preco + "]";
    }

    public boolean adicionarIngrediente(Alimento alim) {
        listaAlimentos.add(alim);
        return true;
    }

    @Override
    public int compareTo(Prato p) {
        if (totalCalorias() < p.totalCalorias())
            return -1;
        else if (totalCalorias() > p.totalCalorias())
            return 1;
        return 0;
    }


    public String alimentosInfo() {
        String msg = "";

        for (Alimento alimento : listaAlimentos) {
        	msg = msg.concat( alimento.toString() );
		}
        return msg;
    }


    public double totalPeso() {
        double p = 0;
        for (Alimento alimento : listaAlimentos) {
        	p = p +  alimento.getPeso();
		}
        return p;
    }


    public double totalCalorias() {
        Double parcial;
        double calorias = 0.0;
        
        for (Alimento alim : listaAlimentos) {
        	parcial = alim.getCalorias();
            calorias = calorias + parcial;
		}
        return calorias;
    }


    public double totalProteinas() {
    	double parcial = 0, proteinas = 0;
    	for (Alimento alim : listaAlimentos) {
        	parcial = alim.getCalorias();
            proteinas = proteinas + parcial;
		}
        return proteinas;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
