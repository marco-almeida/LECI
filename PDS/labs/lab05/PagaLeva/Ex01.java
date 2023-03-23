package lab05.PagaLeva;

public class Ex01 {
    public static void main(String[] args) {
        final int MENUS = 4;
        Portion[] menu = new Portion[MENUS];
        menu[0] = PortionFactory.create("Beverage", Temperature.COLD);
        menu[1] = PortionFactory.create("Meat",Temperature.WARM);
        menu[2] = PortionFactory.create("Beverage",Temperature.WARM);
        menu[3] = PortionFactory.create("Meat",Temperature.COLD);
        System.out.println("---- Thank you for choosing your meal! ----");
        for (Portion p : menu)
            System.out.println(p);
        Container[] containers = new Container[MENUS];
        for (int m = 0; m < MENUS; m++) {
            containers[m] = Container.create(menu[m]);
        }
        System.out.println("---- Take the packages: ----");
        for (Container c : containers) {
            System.out.println(c);
        }
        // Desired output
        /*
        ---- Thank you for choosing your meal! ----
        FruitJuice: Orange, Temperature COLD, State Liquid
        Pork: Temperature WARM, StateSolid
        Milk: Temperature WARM, State Liquid
        Tuna: Temperature COLD, State Solid
        ---- Take the packages: ----
        PlasticBottle with portion = FruitJuice: Orange, Temperature COLD, State Liquid
        Tupperware with portion = Pork: Temperature WARM, StateSolid
        TermicBottle with portion = Milk: Temperature WARM, State Liquid
        PlasticBag with portion = Tuna: Temperature COLD, State Solid
         */
    }
}
