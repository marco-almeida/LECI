package lab05.PagaLeva;

public class Tuna implements Portion {
    private Temperature temperature = Temperature.COLD;
    private State state = State.Solid;

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
