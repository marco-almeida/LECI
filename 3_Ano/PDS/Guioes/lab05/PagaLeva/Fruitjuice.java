package lab05.PagaLeva;

public class Fruitjuice implements Portion {
    private String fruitName;
    private Temperature temperature = Temperature.COLD;
    private State state = State.Liquid;

    @Override
    public String toString() {
        return String.format("%s: %s, Temperature %s, State %s", this.getClass().getSimpleName(), getFruit(), getTemperature().name(), getState().name());
    }

    protected Fruitjuice(String fruitName) {
        this.fruitName = fruitName;
    }

    @Override
    public Temperature getTemperature() {
        return temperature;
    }

    @Override
    public State getState() {
        return state;
    }

    public String getFruit() {
        return fruitName;
    }
}
