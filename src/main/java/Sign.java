import java.util.Objects;

public class Sign {
    private char sign;
    private double probabilityPoint;
    private String code;

    public Sign(char sign, double probabilityPoint) {
        this.sign = sign;
        this.probabilityPoint = probabilityPoint;
    }


    public char getSign() {
        return sign;
    }

    public double getProbabilityPoint() {
        return probabilityPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sign sign1 = (Sign) o;
        return sign == sign1.sign &&
                Double.compare(sign1.probabilityPoint, probabilityPoint) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sign, probabilityPoint);
    }


    @Override
    public String toString() {
        return "Sign{" +
                "sign=" + sign +
                ", probabilityPoint=" + probabilityPoint +
                ", code='" + code + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
