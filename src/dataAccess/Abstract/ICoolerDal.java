package dataAccess.Abstract;

import business.exception.RecordNotFoundException;
import business.exception.UniqueEntityException;
import entity.Concrete.Actuator;
import entity.Concrete.CentralProcessorUnit;
import entity.Concrete.Cooler;
import entity.Concrete.TemperatureSensor;

public interface ICoolerDal {
    void add(Cooler cooler,CentralProcessorUnit centralProcessorUnit, Actuator actuator,TemperatureSensor temperatureSensor)throws UniqueEntityException;    
    void updateInstantWorkingStatus(Cooler cooler);
    String queryStatusOfCooler(String enteredProductId) throws RecordNotFoundException;
    String queryProductId(String enteredProductId) throws UniqueEntityException;
    int queryCoolerId(String enteredProductId) throws UniqueEntityException;
    int queryCentralProcessorUnitId(String enteredProductId) throws UniqueEntityException;
    int queryAcuatorId(String enteredProductId) throws UniqueEntityException;
    int queryTemperatureSensorId(String enteredProductId) throws UniqueEntityException;
    String whatProductOfUser(String enteredUsername);
}
