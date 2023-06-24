package lab05.PagaLeva;

public class PortionFactory {
    public static Portion create(String mealType, Temperature temp) {
        Portion p = null;
        if (mealType.equalsIgnoreCase("Beverage")) {
            if (temp == Temperature.COLD) {
                p = new Fruitjuice("Orange");
            } else if (temp == Temperature.WARM) {
                p = new Milk();
            }
        } else {
            if (temp == Temperature.COLD) {
                p = new Tuna();
            } else {
                // fall back
                p = new Pork();
            }
        }
        return p;
    }
}
