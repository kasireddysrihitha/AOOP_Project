import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {

    private final String cartId;
    private final Currency currency;
    private final List<CartLine> items = new ArrayList<>();

    public Cart(String cartId, Currency currency) {
        this.cartId = Objects.requireNonNull(cartId);
        this.currency = Objects.requireNonNull(currency);

        if (cartId.isBlank()) {
            throw new IllegalArgumentException("Cart ID cannot be empty");
        }
    }

    public void addItem(Product product, Quantity quantity) {
        Objects.requireNonNull(product);
        Objects.requireNonNull(quantity);

        if (product.getPrice().getCurrency() != currency) {
            throw new IllegalArgumentException("Currency mismatch");
        }

        items.add(new CartLine(product, quantity));
    }

    public Money getTotal() {
        Money total = new Money(
            java.math.BigDecimal.ZERO, currency);

        for (CartLine item : items) {
            total = total.add(item.getLineTotal());
        }

        return total;
    }

    public String getCartId() {
        return cartId;
    }

    public List<CartLine> getItems() {
        return List.copyOf(items);
    }
}