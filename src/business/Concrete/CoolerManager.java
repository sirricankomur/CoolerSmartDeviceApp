package business.Concrete;

import business.Abstract.ICoolerService;
import business.exception.RecordNotFoundException;
import business.exception.UniqueEntityException;
import dataAccess.Concrete.CoolerDal;
import entity.Concrete.Actuator;
import entity.Concrete.CentralProcessorUnit;
import entity.Concrete.Cooler;
import entity.Concrete.TemperatureSensor;
import java.sql.SQLException;

public class CoolerManager implements ICoolerService{
    private static CoolerManager coolerManager;
    private CoolerDal coolerDal;
    
    private CoolerManager() {
    }
    
    public static CoolerManager createAsSingleton() {
        if (coolerManager == null) {
            coolerManager = new CoolerManager();
        }
        return coolerManager;
    }
    
    public void add(Cooler cooler, CentralProcessorUnit centralProcessorUnit, Actuator actuator,TemperatureSensor temperatureSensor) throws UniqueEntityException, SQLException{
        coolerDal = CoolerDal.createAsSingleton();
        coolerDal.add(cooler, centralProcessorUnit, actuator, temperatureSensor);
    }
    
    public void updateInstantWorkingStatus(Cooler cooler){
        coolerDal = CoolerDal.createAsSingleton();
        coolerDal.updateInstantWorkingStatus(cooler);
    }
    
    public String whatProductOfUser(String enteredUsername){
        coolerDal = CoolerDal.createAsSingleton();
        return coolerDal.whatProductOfUser(enteredUsername);
    }
    
    public int queryCoolerId(String enteredProductId) throws UniqueEntityException{
        coolerDal = CoolerDal.createAsSingleton();
        return coolerDal.queryCoolerId(enteredProductId);
    }
    
    public String queryStatusOfCooler(String enteredProductId) throws RecordNotFoundException{
        coolerDal = CoolerDal.createAsSingleton();
        return coolerDal.queryStatusOfCooler(enteredProductId);
    }
    
    public int queryCentralProcessorUnitId(String enteredProductId) throws UniqueEntityException{
        coolerDal = CoolerDal.createAsSingleton();
        return coolerDal.queryCentralProcessorUnitId(enteredProductId);
    }
    
    public int queryAcuatorId(String enteredProductId) throws UniqueEntityException{
        coolerDal = CoolerDal.createAsSingleton();
        return coolerDal.queryAcuatorId(enteredProductId);
    }
    
    public int queryTemperatureSensorId(String enteredProductId) throws UniqueEntityException{
        coolerDal = CoolerDal.createAsSingleton();
        return coolerDal.queryTemperatureSensorId(enteredProductId);
    }
}
