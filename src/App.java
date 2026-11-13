import java.math.BigDecimal;

public class App {

    public static void main(String[] args) {

        Product laptop = new Product(
            "P101",
            "Laptop",
            new Money(new BigDecimal("1200.00"), Currency.USD),
            new Percentage(new BigDecimal("10"))
        );

        Cart cart = new Cart("C001", Currency.USD);

        cart.addItem(laptop, new Quantity(2));

        System.out.println("Cart ID: " + cart.getCartId());
        System.out.println("Cart Items:");

        for (CartLine item : cart.getItems()) {
            System.out.println(item);
        }

        System.out.println("Cart Total: " + cart.getTotal());

        System.out.println("\nTesting currency mismatch:");

        Product phone = new Product(
            "P102",
            "Phone",
            new Money(new BigDecimal("500.00"), Currency.EUR),
            new Percentage(BigDecimal.ZERO)
        );

        try {
            cart.addItem(phone, new Quantity(1));
        } catch (IllegalArgumentException e) {
            System.out.println("Error handled: " + e.getMessage());
        }
    }
}