package lab05.PagaLeva;

public class Milk implements Portion {
    private Temperature temperature = Temperature.WARM;
    private State state = State.Liquid;

    @Override
    public String toString() {
        return String.format("%s: Temperature %s, State %s", this.getClass().getSimpleName(), getTemperature().name(), getState().name());
    }

    @Override
    public Temperature getTemperature() {
        return temperature;
    }

    @Override
    public State getState() {
        return state;
    }
}
