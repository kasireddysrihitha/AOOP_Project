public class CartLine {

    private final Product product;
    private final Quantity quantity;

    public CartLine(Product product, Quantity quantity) {
        if (product == null || quantity == null) {
            throw new IllegalArgumentException(
                "Product and quantity cannot be null");
        }

        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Money getLineTotal() {
        return product.getDiscountedPrice().multiply(quantity);
    }

    @Override
    public String toString() {
        return product.getName() + " x " + quantity
            + " = " + getLineTotal();
    }
}