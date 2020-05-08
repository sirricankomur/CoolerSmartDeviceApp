package business.validationRules;

import business.Abstract.ISignUpValidator;
import business.exception.NameLengthException;

public class PasswordSignUpValidator implements ISignUpValidator{
    private static PasswordSignUpValidator passwordSignUpValidator;
    
    private PasswordSignUpValidator() {
    }
    
    public static PasswordSignUpValidator createAsSingleton(){
        if(passwordSignUpValidator == null){
            passwordSignUpValidator = new PasswordSignUpValidator();
        }
        return passwordSignUpValidator;
    }
    @Override
    public boolean limitTo(String enteredPassword) throws NameLengthException{
        boolean result = false;
        if(enteredPassword.length() < 8){
            throw new NameLengthException("The password cannot be smaller than 8 characters.");
        }else{
            result = true;
        }
        return result;
    }
    
}
