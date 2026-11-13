
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {

    private final BigDecimal amount;
    private final Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        Objects.requireNonNull(amount);
        this.currency = Objects.requireNonNull(currency);

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        this.amount = amount.setScale(
            currency.getMinorUnits(), RoundingMode.UNNECESSARY);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money add(Money other) {
        checkCurrency(other);
        return new Money(amount.add(other.amount), currency);
    }

    public Money multiply(Quantity quantity) {
        return new Money(
            amount.multiply(BigDecimal.valueOf(quantity.getValue())),
            currency
        );
    }

    public Money applyDiscount(Percentage percentage) {
        BigDecimal discount = percentage.getValue()
            .divide(new BigDecimal("100"));

        BigDecimal result = amount.multiply(
            BigDecimal.ONE.subtract(discount)
        );

        result = result.setScale(
            currency.getMinorUnits(), RoundingMode.HALF_EVEN);

        return new Money(result, currency);
    }

    private void checkCurrency(Money other) {
        if (other == null || currency != other.currency) {
            throw new IllegalArgumentException("Currency mismatch");
        }
    }

    @Override
    public String toString() {
        return currency + " " + amount;
    }
}
