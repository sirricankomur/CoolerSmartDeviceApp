package business.Concrete;

import business.Abstract.ICoolerService;
import business.exception.UniqueEntityException;
import dataAccess.Concrete.CentralProcessorUnitDal;
import entity.Concrete.CentralProcessorUnit;
import entity.Concrete.Cooler;
import java.util.Random;

public class CentralProcessorUnitManager implements ICoolerService{
    private static CentralProcessorUnitManager centralProcessorUnitManager;
    private CentralProcessorUnitDal centralProcessorUnitDal;
    private Random random;
    private CentralProcessorUnitManager() {
    }
    
    public static CentralProcessorUnitManager createAsSingleton() {
        if (centralProcessorUnitManager == null) {
            centralProcessorUnitManager = new CentralProcessorUnitManager();
        }
        return centralProcessorUnitManager;
    }
    
    public int generateRandomPartNumber() throws UniqueEntityException {
        random = new Random();
        return random.nextInt(100000000);
    }
    
    public void add(CentralProcessorUnit centralProcessorUnit) throws UniqueEntityException{
        centralProcessorUnitDal = CentralProcessorUnitDal.createAsSingleton();
        centralProcessorUnitDal.add(centralProcessorUnit);
    }
    
    public void turnOnCentralProcessorUnit(Cooler cooler){
        centralProcessorUnitDal = CentralProcessorUnitDal.createAsSingleton();
        centralProcessorUnitDal.turnOnCentralProcessorUnit(cooler);
    }
    
    public void turnOffCentralProcessorUnit(Cooler cooler){
        centralProcessorUnitDal = CentralProcessorUnitDal.createAsSingleton();
        centralProcessorUnitDal.turnOffCentralProcessorUnit(cooler);
    }
    
    public void sleepOnCooler(Cooler cooler){
        centralProcessorUnitDal = CentralProcessorUnitDal.createAsSingleton();
        centralProcessorUnitDal.sleepOnCooler(cooler);
    }
    
    public void sleepOffCooler(Cooler cooler){
        centralProcessorUnitDal = CentralProcessorUnitDal.createAsSingleton();
        centralProcessorUnitDal.sleepOffCooler(cooler);
    }
    
    public int queryProductId(int enteredProductId) throws UniqueEntityException{
        centralProcessorUnitDal = CentralProcessorUnitDal.createAsSingleton();
        return centralProcessorUnitDal.queryProductId(enteredProductId);
    }
    
    public int whatIsTheId(int enteredProductId) throws UniqueEntityException{
        centralProcessorUnitDal = CentralProcessorUnitDal.createAsSingleton();
        return centralProcessorUnitDal.whatIsTheId(enteredProductId);
    }
}
