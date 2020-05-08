package business.validationRules;

import business.exception.NameLengthException;
import business.Abstract.ISignUpValidator;

public class FirstNameSignUpValidator implements ISignUpValidator{
    private static FirstNameSignUpValidator firstNameValidator;
    
    private FirstNameSignUpValidator() {
    }
    
    public static FirstNameSignUpValidator createAsSingleton(){
        if(firstNameValidator == null){
            firstNameValidator = new FirstNameSignUpValidator();
        }
        return firstNameValidator;
    }
    
    @Override
    public boolean limitTo(String enteredFirstName) throws NameLengthException{
        boolean result = false;
        if(enteredFirstName.length() < 2){
            throw new NameLengthException("The first name cannot be smaller than 2 characters.");
        }else{
            result = true;
        }
        return result;
    }  
}
