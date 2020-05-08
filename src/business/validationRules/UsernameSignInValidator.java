package business.validationRules;

import business.exception.RecordNotFoundException;
import dataAccess.Concrete.DbHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import business.Abstract.ISignInValidator;

public class UsernameSignInValidator implements ISignInValidator{
    private static UsernameSignInValidator usernameSignInValidator;
    private static DbHelper dbHelper;
    
    private UsernameSignInValidator(){}
    
    public static UsernameSignInValidator createAsSingleton(){
        if(usernameSignInValidator == null){
            usernameSignInValidator = new UsernameSignInValidator();
        }
        return usernameSignInValidator;
    }
    
    @Override
    public boolean validate(String enteredOriginalUsername, String enteredUsername) throws RecordNotFoundException{
        dbHelper = DbHelper.createAsSingleton();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        
        try {
            connection = dbHelper.getConnection();
            
            String sql = "select username from internet_users where username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,enteredUsername);
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                enteredOriginalUsername = resultSet.getString("username");
            }
            
            if (isThereAnyUser(enteredOriginalUsername)) {
                result = true;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(UsernameSignInValidator.class.getName()).log(Level.SEVERE, null, ex);
            
        }  finally{
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsernameSignInValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public boolean isThereAnyUser(String username) throws RecordNotFoundException{
        if(username == null){
            throw new RecordNotFoundException("Invalid the username or password.");
        }else{
            return true;
        }
    }
}
