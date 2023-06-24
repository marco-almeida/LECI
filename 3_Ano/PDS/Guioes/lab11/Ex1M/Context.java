package lab11.Ex1M;

public class Context {
    private Strategy opStrategy;

    public Context(Strategy operation) {
        this.opStrategy = operation;
    }

    public void sortBy(String attribute) {
        opStrategy.sortBy(attribute);
    }

    public void setStrategy(Strategy strategy) {
        opStrategy = strategy;
    }
}
