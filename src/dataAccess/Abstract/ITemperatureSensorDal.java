package dataAccess.Abstract;

import business.exception.UniqueEntityException;
import entity.Concrete.TemperatureSensor;

public interface ITemperatureSensorDal {
    public void add(TemperatureSensor temperatureSensor) throws UniqueEntityException;
    void update(TemperatureSensor temperatureSensor);
    int whatIsTheId(int enteredProductId);
    int queryProductId(int enteredProductId);
    void queryLastTemperatureValue(TemperatureSensor temperatureSensor);
}
