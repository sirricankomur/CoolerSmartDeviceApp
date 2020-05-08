package business.validationRules;

import business.Abstract.IEmailValidator;
import business.exception.EmailConstraintException;
import business.exception.NameLengthException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import business.Abstract.ISignUpValidator;

public class EmailSignUpValidator implements ISignUpValidator, IEmailValidator{
    private static EmailSignUpValidator emailValidator;
    private static final String MAIL_CONSTRAINTS = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
    private int mailCounter = 0;
    
    
    private EmailSignUpValidator() {
    }
    
    public static EmailSignUpValidator createAsSingleton(){
        if(emailValidator == null){
            emailValidator = new EmailSignUpValidator();
        }
        return emailValidator;
    }
    @Override
    public boolean limitTo(String enteredEmail) throws NameLengthException{
        boolean result = false;
        if(enteredEmail.length() == 0){
            throw new NameLengthException("Please enter your email.");
        }else{
            result = true;
        }
        return result;
    }
    
    @Override
    public boolean validate(String enteredEmail) throws EmailConstraintException {
        Pattern pattern = Pattern.compile(MAIL_CONSTRAINTS);
        //Eşleşmenin yapılacağı yapı atanıyor.
        Matcher matcher = pattern.matcher(enteredEmail);
        
        boolean result = false;
        
        //Eşleşir mi?
        if (matcher.matches()) {
            result = true;
        }else{
            throw new EmailConstraintException("Invalid Email.");
        }
        return result;
    }
}
