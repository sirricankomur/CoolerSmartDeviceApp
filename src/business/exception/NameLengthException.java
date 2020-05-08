package business.exception;

public class NameLengthException extends Exception{
    private String message;
    
    public NameLengthException(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
}
