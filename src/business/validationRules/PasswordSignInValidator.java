package business.validationRules;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import business.conversion.ConversionProcess;
import business.exception.RecordNotFoundException;
import dataAccess.Concrete.DbHelper;
import java.sql.*;
import business.Abstract.ISignInValidator;

public class PasswordSignInValidator implements ISignInValidator {
    private static PasswordSignInValidator passwordValidator;
    private ConversionProcess conversionProcess;
    private DbHelper dbHelper;
    
    private PasswordSignInValidator() {
    }
    
    public static PasswordSignInValidator createAsSingleton(){
        if(passwordValidator == null){
            passwordValidator = new PasswordSignInValidator();
        }
        return passwordValidator;
    }
    
    @Override
    public boolean validate(String enteredUsername, String enteredPassword) throws RecordNotFoundException {
        dbHelper = DbHelper.createAsSingleton();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String originalPassword = null;
        boolean result = false;
        
        try {
            connection = dbHelper.getConnection();
            
            String sql = "select password_hash from internet_users where username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,enteredUsername);
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                originalPassword = resultSet.getString("password_hash");
            }
            
            if (isThereAnyUser(originalPassword, enteredPassword)) {
                result = true;
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(PasswordSignInValidator.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordSignInValidator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PasswordSignInValidator.class.getName()).log(Level.SEVERE, null, ex);
        }    finally{
            
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(PasswordSignInValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    boolean validatePassword(String originalPassword, String enteredPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        conversionProcess = new ConversionProcess();
        
        String[] enteredPasswordParts = originalPassword.split(":");
        int iteration = Integer.parseInt(enteredPasswordParts[0]);
        byte[] salt = conversionProcess.convertHexToBytes(enteredPasswordParts[1]);
        byte[] hash = conversionProcess.convertHexToBytes(enteredPasswordParts[2]);
        
        PBEKeySpec pbeKeySpec = new PBEKeySpec(enteredPassword.toCharArray(), salt, iteration, hash.length * 8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] testHash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        
        int diff = hash.length ^ testHash.length;
        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
    
    public boolean equalsSlow(byte[] b1, byte[] b2){
        int diff = b1.length ^ b2.length;
        for (int i = 0; i < b1.length && i < b2.length; i++) {
            diff |= b1[i] ^ b2[i];
        }
        return diff == 0;
    }
    
    public boolean isThereAnyUser(String original, String password) throws RecordNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException{
        if(!validatePassword(original, password)) {
            throw new RecordNotFoundException("Invalid the username or password.");
        }else{
            System.out.println("Password correct");
            return true;
        }
    }
}
