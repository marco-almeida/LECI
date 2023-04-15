package lab07.CabazesCompras;

import java.util.ArrayList;
import java.util.List;

public class Caixa implements Component { // podia dar extend a Product i guess
    private final String name;
    private final double weight;
    private final List<Component> elements;

    public Caixa(String name, double weight) {
        this.name = name;
        this.weight = weight;
        elements = new ArrayList<>();
    }

    public void add(Component c) {
        elements.add(c);
    }

    public void draw() {
        draw(0);
    }

    private void draw(int depth) {
        System.out.println(this);
        for (Component c : elements) {
            System.out.print("   ".repeat(depth + 1));
            if (c instanceof Caixa caixa) {
                caixa.draw(depth + 1);
            } else {
                System.out.println(c);
            }
        }
    }

    public double getTotalWeight() {
        return weight + getChildrenWeight();
    }

    private double getChildrenWeight() {
        int total = 0;
        for (Component c : elements) {
            if (c instanceof Caixa caixa) {
                total += caixa.getTotalWeight();
            } else {
                total += c.getWeight();
            }
        }
        return total;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("* %s '%s' [ Weight : %.1f ; Total: %.1f]", this.getClass()
                .getSimpleName(), name, (float) weight, (float) getTotalWeight());
    }

}
