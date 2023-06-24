public class Scalar extends Value {
    private double value;

    public Scalar(double value) {
        this.value = value;
        setScalar(true);
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public Value symmetric() {
        return new Scalar(-value);
    }

    @Override
    public Value sum(Value v) {
        return new Scalar(this.value + ((Scalar) v).getValue());
    }

    @Override
    public Value subtract(Value v) {
        return new Scalar(this.value - ((Scalar) v).getValue());
    }

    public double getValue() {
        return value;
    }

    @Override
    public Value multiply(Value v) {
        if (v instanceof Vector) {
            return v.multiply(this);
        } else {
            return new Scalar(this.value * ((Scalar) v).getValue());
        }
    }

}
