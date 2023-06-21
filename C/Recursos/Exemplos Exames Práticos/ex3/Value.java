/**
 * Value
 */
public abstract class Value {
    private boolean isVector;
    private boolean isScalar;

    public abstract Value symmetric();

    public abstract Value sum(Value v);

    public abstract Value subtract(Value v);

    public abstract Value multiply(Value v);

    public boolean isVector() {
        return isVector;
    }

    public boolean isScalar() {
        return isScalar;
    }

    public void setVector(boolean isVector) {
        this.isVector = isVector;
    }

    public void setScalar(boolean isScalar) {
        this.isScalar = isScalar;
    }

}