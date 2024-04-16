package Utilities;
//In order to implement Epic Feature 12 we created these four classes to handle our exceptions.

public class NegativeSalaryException extends RuntimeException{
    public NegativeSalaryException(String message) throws RuntimeException {
        super(message);
    }
}
