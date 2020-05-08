package business.validationRules;

import business.exception.RecordNotFoundException;
import business.Abstract.ISignInValidator;

public class UserSignInValidator implements ISignInValidator{
    private static UserSignInValidator userValidator;
    private UsernameSignInValidator usernameValidator;
    private PasswordSignInValidator passwordValidator;
    
    private UserSignInValidator() {}
    
    public static UserSignInValidator createAsSingleton(){
        if(userValidator == null){
            userValidator = new UserSignInValidator();
        }
        return userValidator;
    }
    
    @Override
    public boolean validate(String enteredUsername, String enteredPassword)throws RecordNotFoundException {
        usernameValidator = UsernameSignInValidator.createAsSingleton();
        passwordValidator = PasswordSignInValidator.createAsSingleton();
        
        String enteredOriginalUsername = null;
        boolean result = false;
        
        if(usernameValidator.validate(enteredOriginalUsername, enteredUsername)&&
                passwordValidator.validate(enteredUsername, enteredPassword)){
            result = true;
            
        }
        
        return result;
    }
    
}
