package dataAccess.Abstract;

import business.exception.UniqueEntityException;
import entity.Concrete.CentralProcessorUnit;
import entity.Concrete.Cooler;

public interface ICentralProcessorUnitDal {
    
    public void add(CentralProcessorUnit centralProcessorUnit) throws UniqueEntityException;
    void turnOnCentralProcessorUnit(Cooler cooler);
    void turnOffCentralProcessorUnit(Cooler cooler);
    void sleepOnCooler(Cooler cooler);
    void sleepOffCooler(Cooler cooler);
    int whatIsTheId(int enteredProductId);
    int queryProductId(int enteredProductId) throws UniqueEntityException;
}
