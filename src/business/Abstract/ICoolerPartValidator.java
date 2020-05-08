package business.Abstract;

import business.exception.UniqueEntityException;

public interface ICoolerPartValidator {
    boolean isThereProductId(int enteredProductId) throws UniqueEntityException;
}
