package dataAccess.Abstract;

import business.exception.UniqueEntityException;
import entity.Concrete.Actuator;
import entity.Concrete.Cooler;

public interface IActuatorDal {
    void add(Actuator actuator) throws UniqueEntityException;
    void turnOnActuator(Cooler cooler);
    void turnOffActuator(Cooler cooler);   
    int whatIsTheId(int enteredProductId);
    int queryProductId(int enteredProductId) throws UniqueEntityException;
}
