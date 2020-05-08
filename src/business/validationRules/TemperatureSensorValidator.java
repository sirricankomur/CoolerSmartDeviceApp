package business.validationRules;

import business.Abstract.ICoolerPartValidator;
import business.exception.UniqueEntityException;

public class TemperatureSensorValidator implements ICoolerPartValidator{
    private static TemperatureSensorValidator temperatureSensorValidator;
    
    private TemperatureSensorValidator() {
    }
    
    public static TemperatureSensorValidator createAsSingleton(){
        if(temperatureSensorValidator == null){
            temperatureSensorValidator = new TemperatureSensorValidator();
        }
        return temperatureSensorValidator;
    }
    
    @Override
    public boolean isThereProductId(int enteredProductId) throws UniqueEntityException{
        if(enteredProductId != 0){
            throw new UniqueEntityException("Registered temperature.");
        }else{
            return true;
        }
    }
}
