package operation.exception;

public class NotEnoughCreditException extends RuntimeException {
    public NotEnoughCreditException() {
        super("You didn´t introduce enough money to purchase this product");
    }
}
