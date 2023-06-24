package lab05.PagaLeva;

public class Container {
    private Portion portion;
    public static Container create(Portion portion) {
        if (portion == null) {
            return null;
        }
        Container container;
        if (portion.getState() == State.Liquid) {
            if (portion.getTemperature() == Temperature.COLD) {
                container = new PlasticBottle();
                container.setPortion(portion);
                return container;
            } else if (portion.getTemperature() == Temperature.WARM) {
                container = new TermicBottle();
                container.setPortion(portion);
                return container;
            }
        } else if (portion.getState() == State.Solid) {
            if (portion.getTemperature() == Temperature.COLD) {
                container = new PlasticBag();
                container.setPortion(portion);
                return container;
            } else if (portion.getTemperature() == Temperature.WARM) {
                container = new Tupperware();
                container.setPortion(portion);
                return container;
            }
        }
        return null;
    }

    public void setPortion(Portion p) {
        portion = p;
    }

    public String toString() {
        return String.format("%s with portion = %s", this.getClass().getSimpleName(), portion);
    }

}
