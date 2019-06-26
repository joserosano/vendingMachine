package operation.exception;

public class ChangeNotAvailableException extends RuntimeException {
    public ChangeNotAvailableException() {
        super("Change not available for the desired product");
    }
}
