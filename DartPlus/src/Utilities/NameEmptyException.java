package Utilities;

public class NameEmptyException extends RuntimeException {
    public NameEmptyException(String message) throws RuntimeException {
        super(message);
    }
}
