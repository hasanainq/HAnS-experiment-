package Utilities;

public class EarlyDateException extends RuntimeException {
    public EarlyDateException(String message) throws RuntimeException {
        super(message);
    }
}
