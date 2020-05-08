package business.validationRules;

import business.Abstract.ICoolerPartValidator;
import business.exception.UniqueEntityException;

public class CentralProcessorUnitValidator implements ICoolerPartValidator{
    private static CentralProcessorUnitValidator centralProcessorUnitValidator;
    
    private CentralProcessorUnitValidator() {
    }
    
    public static CentralProcessorUnitValidator createAsSingleton(){
        if(centralProcessorUnitValidator == null){
            centralProcessorUnitValidator = new CentralProcessorUnitValidator();
        }
        return centralProcessorUnitValidator;
    }
    
    @Override
    public boolean isThereProductId(int enteredProductId) throws UniqueEntityException{
        if(enteredProductId != 0){
            throw new UniqueEntityException("Registered central.");
        }else{
            return true;
        }
    } 
}
