package business.validationRules;

import business.Abstract.ISignUpValidator;
import business.exception.NameLengthException;

public class UsernameSignUpValidator implements ISignUpValidator{
    private static UsernameSignUpValidator usernameSignUpValidator;
    
    private UsernameSignUpValidator() {
    }
    
    public static UsernameSignUpValidator createAsSingleton(){
        if(usernameSignUpValidator == null){
            usernameSignUpValidator = new UsernameSignUpValidator();
        }
        return usernameSignUpValidator;
    }
    @Override
    public boolean limitTo(String enteredUsername) throws NameLengthException{
        boolean result = false;
        if(enteredUsername.length() < 5){
            throw new NameLengthException("The username cannot be smaller than 5 characters.");
        }else{
            result = true;
        }
        return result;
    }
}
