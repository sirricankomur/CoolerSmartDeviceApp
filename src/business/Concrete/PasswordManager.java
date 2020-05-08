package business.Concrete;

import business.Abstract.IUserService;
import business.conversion.ConversionProcess;
import dataAccess.Concrete.DbHelper;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PasswordManager implements IUserService{
    private static PasswordManager passwordManager;
    private ConversionProcess conversionProcess = null;
    private DbHelper dbHelper = null;
    
    private PasswordManager(){
        
    }
    
    public static PasswordManager createAsSingleton(){
        if(passwordManager == null){
            passwordManager = new PasswordManager();
        }
        return passwordManager;
    }
    
    public byte[] createSalt() {
        byte[] salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return salt;
    }
    
    public String generatePasswordHash(String password) {
        conversionProcess = new ConversionProcess();
        int iteration = 10000;
        char[] charsOfPassword = password.toCharArray();
        byte[] salt = createSalt();
        byte[] hash = null;
        int hashLength = 64;
        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt, iteration, hashLength * 8);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            hash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PasswordManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return iteration + ":" + conversionProcess.convertBytesToHex(salt) + ":" + conversionProcess.convertBytesToHex(hash);
    }
}
