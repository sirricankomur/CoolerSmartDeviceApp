package business.validationRules;

import business.Abstract.ISignUpValidator;
import business.exception.NameLengthException;
import business.exception.RecordNotFoundException;
import business.exception.UniqueEntityException;

public class CoolerProductIdValidator implements ISignUpValidator{
    private static CoolerProductIdValidator coolerProductIdValidator;
    
    private CoolerProductIdValidator() {
    }
    
    public static CoolerProductIdValidator createAsSingleton(){
        if(coolerProductIdValidator == null){
            coolerProductIdValidator = new CoolerProductIdValidator();
        }
        return coolerProductIdValidator;
    }
    
    @Override
    public boolean limitTo(String enteredProductId) throws NameLengthException {
        boolean result = false;
        if(enteredProductId.length() < 10 || enteredProductId.length() > 20){
            throw new NameLengthException("The Product ID must be between 10 and 20 characters.");
        }else{
            result = true;
        }
        return result;
    }
    
    public boolean isThereProduct(String instantWorkingStatus) throws RecordNotFoundException{
        if(instantWorkingStatus == null){
            throw new RecordNotFoundException("Product not found.");
        }else{
            return true;
        }
    }
    
    public boolean isThereProductId(String enteredProductId) throws UniqueEntityException{
        if(enteredProductId != null){
            throw new UniqueEntityException("Registered cooler.");
        }else{
            return true;
        }
    }
}
