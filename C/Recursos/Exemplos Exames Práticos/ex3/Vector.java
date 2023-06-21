import java.util.ArrayList;
import java.util.List;

public class Vector extends Value {
    private List<Double> values = new ArrayList<>();

    public Vector(List<Double> values) {
        this.values = values;
        setVector(true);
    }

    public Vector(String values) {
        setVector(true);
        values = values.replace("[", "");
        values = values.replace("]", "");
        String[] unparsedNumbers = values.split(",");
        for (String number : unparsedNumbers) {
            this.values.add(Double.parseDouble(number));
        }
    }

    @Override
    public String toString() {
        return values.toString();
    }

    @Override
    public Value symmetric() {
        List<Double> tmp = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            tmp.add(i, values.get(i) * -1);
        }
        return new Vector(tmp);
    }

    @Override
    public Value sum(Value v) {
        List<Double> tmp = new ArrayList<>();
        Vector v1 = (Vector) v;
        for (int i = 0; i < this.values.size() && i < v1.getValues().size(); i++) {
            tmp.add(i, this.values.get(i) + v1.getValues().get(i));
        }
        return new Vector(tmp);
    }

    @Override
    public Value subtract(Value v) {
        List<Double> tmp = new ArrayList<>();
        Vector v1 = (Vector) v;
        for (int i = 0; i < this.values.size() && i < v1.getValues().size(); i++) {
            tmp.add(i, this.values.get(i) - v1.getValues().get(i));
        }
        return new Vector(tmp);
    }

    public List<Double> getValues() {
        return values;
    }

    @Override
    public Value multiply(Value v) {
        Scalar c = ((Scalar) v);
        List<Double> tmp = new ArrayList<>();
        for (int i = 0; i < this.values.size(); i++) {
            tmp.add(this.values.get(i) * c.getValue());
        }
        return new Vector(tmp);
    }

    public Value internalProduct(Value v) {
        Vector c1 = ((Vector) v);
        double c1Prod = 1;
        for (double d : c1.getValues()) {
            c1Prod *= d;
        }
        double myProd = 1;
        for (double d : this.values) {
            myProd *= d;
        }
        return new Scalar(c1Prod + myProd);
    }

}
