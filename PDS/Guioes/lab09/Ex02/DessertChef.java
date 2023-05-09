package lab09.Ex02;

import java.util.concurrent.ThreadLocalRandom;

public class DessertChef extends Chef {
    @Override
    public void cook(String food) {
        if (canHandleFood(food, "ice cream")) {
            System.out.printf("%s: Starting to cook %s. Out in %d minutes!\n", this.getClass()
                    .getSimpleName(), food, ThreadLocalRandom.current()
                    .nextInt(5, 21));
        } else {
            System.out.printf("%s: I can't cook that.\n", this.getClass().getSimpleName());
            super.cook(food);
        }
    }
}
