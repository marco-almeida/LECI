package lab09.Ex02;

import java.util.List;

public class Ex02 {
    public static void main(String[] args) {
        List<String> foods = List.of("veggie burger",
                "Pasta Carbonara",
                "PLAIN pizza, no toppings!",
                "sushi nigiri and sashimi",
                "salad with tuna",
                "ice cream and waffles dessert");

        Chef chefs = new SushiChef()
                .setSuccessor(
                new PastaChef()
                .setSuccessor(
                new BurgerChef()
                .setSuccessor(
                new PizzaChef()
                .setSuccessor(
                new DessertChef()))));
        for (String food : foods) {
            System.out.printf("Can I please get a %s?\n", food);
            chefs.cook(food);
            System.out.println();
        }

    }
}
