package business.Abstract;

import business.exception.RecordNotFoundException;

public interface ISignInValidator {
    boolean validate(String enteredValue1, String enteredValue2) throws RecordNotFoundException;
}
