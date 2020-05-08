package business.Concrete;

import business.Abstract.ICoolerService;
import business.exception.UniqueEntityException;
import dataAccess.Concrete.ActuatorDal;
import entity.Concrete.Actuator;
import entity.Concrete.Cooler;
import java.util.Random;

public class ActuatorManager implements ICoolerService{
    private static ActuatorManager actuatorManager;
    private ActuatorDal actuatorDal;
    private Random random;
    
    private ActuatorManager() {
    }
    
    public static ActuatorManager createAsSingleton() {
        if (actuatorManager == null) {
            actuatorManager = new ActuatorManager();
        }
        return actuatorManager;
    }
    
    public int generateRandomPartNumber() throws UniqueEntityException {
        random = new Random();
        return random.nextInt(100000000);
    }
    
    public void add(Actuator actuator) throws UniqueEntityException {
        actuatorDal = ActuatorDal.createAsSingleton();
        actuatorDal.add(actuator);
    }
    
    public void turnOnActuator(Cooler cooler){
        actuatorDal = ActuatorDal.createAsSingleton();
        actuatorDal.turnOnActuator(cooler);
    }
    
    public void turnOffActuator(Cooler cooler){
        actuatorDal = ActuatorDal.createAsSingleton();
        actuatorDal.turnOffActuator(cooler);
    }
    
    public int queryProductId(int enteredProductId) throws UniqueEntityException{
        actuatorDal = ActuatorDal.createAsSingleton();
        return actuatorDal.queryProductId(enteredProductId);
    }
    
    public int whatIsTheId(int enteredProductId) throws UniqueEntityException{
        actuatorDal = ActuatorDal.createAsSingleton();
        return actuatorDal.whatIsTheId(enteredProductId);
    }
}
