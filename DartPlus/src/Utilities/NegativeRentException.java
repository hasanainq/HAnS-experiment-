package Utilities;

public class NegativeRentException extends RuntimeException {
    public NegativeRentException(String message) throws RuntimeException {
        super(message);
    }
}
