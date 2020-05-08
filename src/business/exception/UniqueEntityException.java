package business.exception;

public class UniqueEntityException extends Exception{
    private String message;
    
    public UniqueEntityException(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
}
