package lab10.Ex1;

import java.util.ArrayList;

public class Leilao {
    ArrayList<Product> listOfProducts;

    public Leilao() {
        listOfProducts = new ArrayList<>();
    }

    public void add(Product product) {
        listOfProducts.add(product);
    }


}
