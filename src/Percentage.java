
import java.math.BigDecimal;
import java.util.Objects;

public class Percentage {

    private final BigDecimal value;

    public Percentage(BigDecimal value) {
        Objects.requireNonNull(value);

        if (value.compareTo(BigDecimal.ZERO) < 0 ||
            value.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException(
                "Percentage must be between 0 and 100");
        }

        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + "%";
    }
}
