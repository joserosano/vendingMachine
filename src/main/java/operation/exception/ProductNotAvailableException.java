package operation.exception;

public class ProductNotAvailableException extends RuntimeException {
    public ProductNotAvailableException() {
        super("Product not available");
    }
}
