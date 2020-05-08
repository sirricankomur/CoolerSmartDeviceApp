package business.validationRules;

import business.exception.NameLengthException;
import business.Abstract.ISignUpValidator;

public class LastNameSignUpValidator implements ISignUpValidator{
    private static LastNameSignUpValidator lastNameValidator;
    
    private LastNameSignUpValidator() {
    }
    
    public static LastNameSignUpValidator createAsSingleton(){
        if(lastNameValidator == null){
            lastNameValidator = new LastNameSignUpValidator();
        }
        return lastNameValidator;
    }
    @Override
    public boolean limitTo(String enteredLastName) throws NameLengthException{
        boolean result = false;
        if(enteredLastName.length() < 2){
            throw new NameLengthException("The last name cannot be smaller than 2 characters.");
        }else{
            result = true;
        }
        return result;
    }
}
