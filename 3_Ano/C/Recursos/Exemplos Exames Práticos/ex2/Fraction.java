/**
 * Fraction
 */
public class Fraction {
    private int num; // numerator
    private int denom; // denominator

    public Fraction(int num, int denom) {
        if (denom < 0) { // para nao termos denominadores negativos
            num = -num;
            denom = -denom;
        }
        this.num = num;
        this.denom = denom;
    }

    public Fraction(int num) {
        this(num, 1);
    }

    private static int mdc(int a, int b) {
        if (b == 0) {
            return a;
        }
        return mdc(b, a % b);
    }

    public static Fraction sumFractions(Fraction f1, Fraction f2) {
        return new Fraction(f1.num * f2.denom + f2.num * f1.denom, f1.denom * f2.denom);
    }

    public static Fraction subtractFractions(Fraction f1, Fraction f2) {
        return new Fraction(f1.num * f2.denom - f2.num * f1.denom, f1.denom * f2.denom);
    }

    public static Fraction multiplyFractions(Fraction f1, Fraction f2) {
        return new Fraction(f1.num * f2.num, f1.denom * f2.denom);
    }

    public static Fraction divideFractions(Fraction f1, Fraction f2) {
        return new Fraction(f1.num * f2.denom, f1.denom * f2.num);
    }

    public static Fraction reduce(Fraction f1) {
        int divider = mdc(f1.num, f1.denom);
        return new Fraction(f1.num / divider, f1.denom / divider);
    }

    @Override
    public String toString() {
        return denom != 1 ? String.format("%d/%d", num, denom) : String.format("%d", num);
    }
}