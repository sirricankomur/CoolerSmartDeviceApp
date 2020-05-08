package business.Abstract;

import business.exception.EmailConstraintException;

public interface IEmailValidator {
    boolean validate(String enteredEmail) throws EmailConstraintException;
}
