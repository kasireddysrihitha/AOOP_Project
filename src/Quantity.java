
public class Quantity {

    private final int value;

    public Quantity(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be positive");
        }

        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
