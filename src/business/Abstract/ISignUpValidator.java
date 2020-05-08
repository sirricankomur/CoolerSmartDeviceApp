package business.Abstract;

import business.exception.NameLengthException;

public interface ISignUpValidator {
    boolean limitTo(String enteredName) throws NameLengthException;
}
