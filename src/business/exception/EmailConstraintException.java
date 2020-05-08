package business.exception;

public class EmailConstraintException extends Exception{
    private String message;
    
    public EmailConstraintException(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
}
