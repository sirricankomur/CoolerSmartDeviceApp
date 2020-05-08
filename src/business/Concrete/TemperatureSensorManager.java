package business.Concrete;

import business.Abstract.ICoolerService;
import business.exception.UniqueEntityException;
import dataAccess.Concrete.TemperatureSensorDal;
import entity.Concrete.TemperatureSensor;
import java.util.Random;

public class TemperatureSensorManager implements ICoolerService{
    private static TemperatureSensorManager temperatureSensorManager;
    private TemperatureSensorDal temperatureSensorDal;
    private Random random;
    private TemperatureSensorManager() {
    }
    
    public static TemperatureSensorManager createAsSingleton() {
        if (temperatureSensorManager == null) {
            temperatureSensorManager = new TemperatureSensorManager();
        }
        return temperatureSensorManager;
    }
    
    public int generateRandomPartNumber() throws UniqueEntityException {
        random = new Random();
        return random.nextInt(10000000);
    }
    
    public void add(TemperatureSensor temperatureSensor) throws UniqueEntityException {
        temperatureSensorDal = temperatureSensorDal.createAsSingleton();
        temperatureSensorDal.add(temperatureSensor);
    }
    
    public void update(TemperatureSensor temperatureSensor){
        temperatureSensorDal = temperatureSensorDal.createAsSingleton();
        temperatureSensorDal.update(temperatureSensor);
    }
    
    public int queryProductId(int enteredProductId) throws UniqueEntityException{
        temperatureSensorDal = temperatureSensorDal.createAsSingleton();
        return temperatureSensorDal.queryProductId(enteredProductId);
    }
    
    public int whatIsTheId(int enteredProductId) throws UniqueEntityException{
        temperatureSensorDal = temperatureSensorDal.createAsSingleton();
        return temperatureSensorDal.whatIsTheId(enteredProductId);
    }
    
    public float measureTemperature(){
        random = new Random();
        return random.nextInt(99) + random.nextFloat();
    }
    
    public void queryLastTemperatureValue(TemperatureSensor temperatureSensor){
        temperatureSensorDal = temperatureSensorDal.createAsSingleton();
        temperatureSensorDal.queryLastTemperatureValue(temperatureSensor);
    }
}
