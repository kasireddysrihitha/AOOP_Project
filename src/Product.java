import java.util.Objects;

public class Product {

    private final String sku;
    private final String name;
    private final Money price;
    private final Percentage discount;

    public Product(String sku, String name, Money price, Percentage discount) {
        this.sku = Objects.requireNonNull(sku);
        this.name = Objects.requireNonNull(name);
        this.price = Objects.requireNonNull(price);
        this.discount = Objects.requireNonNull(discount);

        if (sku.isBlank() || name.isBlank()) {
            throw new IllegalArgumentException(
                "SKU and product name cannot be empty");
        }
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public Percentage getDiscount() {
        return discount;
    }

    public Money getDiscountedPrice() {
        return price.applyDiscount(discount);
    }

    @Override
    public String toString() {
        return name + " (" + sku + ") - " + getDiscountedPrice();
    }
}