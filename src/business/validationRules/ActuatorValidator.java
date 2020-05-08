package business.validationRules;

import business.Abstract.ICoolerPartValidator;
import business.exception.UniqueEntityException;

public class ActuatorValidator implements ICoolerPartValidator{
    private static ActuatorValidator actuatorValidator;
    
    private ActuatorValidator() {
    }
    
    public static ActuatorValidator createAsSingleton(){
        if(actuatorValidator == null){
            actuatorValidator = new ActuatorValidator();
        }
        return actuatorValidator;
    }
    
    @Override
    public boolean isThereProductId(int enteredProductId) throws UniqueEntityException{
        if(enteredProductId != 0){
            throw new UniqueEntityException("Registered actuator.");
        }else{
            return true;
        }
    }
}
