package threads;

public class OptimisticException extends RuntimeException {
    private String message;

    public OptimisticException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
