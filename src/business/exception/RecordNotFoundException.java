package business.exception;

public class RecordNotFoundException extends Exception{
    private String message;
    
    public RecordNotFoundException(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
}
