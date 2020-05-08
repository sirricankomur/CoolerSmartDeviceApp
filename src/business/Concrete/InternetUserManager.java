package business.Concrete;

import business.exception.UniqueEntityException;
import business.observer.Observer;
import dataAccess.Concrete.DbHelper;
import dataAccess.Concrete.InternetUserDal;
import entity.Concrete.InternetUser;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import business.Abstract.IUserService;

public class InternetUserManager implements IUserService{
    private static InternetUserManager internetUserManager;
    private DbHelper dbHelper;
    
    private InternetUserDal internetUserDal;
    List<Observer> observer = new ArrayList<>();
    
    private InternetUserManager() {}
    
    public static InternetUserManager createAsSingleton(){
        if(internetUserManager == null){
            internetUserManager = new InternetUserManager();
        }
        return internetUserManager;
    }
    
    public String signedCooler(Observer observer){
        notifyAllObservers();
        return "Signed in to the system.";
    }
    
    public String updateCooler(){         
        notifyAllObservers();
        return "There is a new update.";
    }
    
    public void attach(Observer observer){
        this.observer.add(observer);
    }
    
    public void detach(Observer observer){
        this.observer.remove(observer);
    }
    
    
    private void notifyAllObservers(){
        for (Observer obs : observer) {
            obs.update();
        }
    }
    
    public void add(InternetUser internetUser) throws SQLException{
        internetUserDal = InternetUserDal.createAsSingleton();
        internetUserDal.add(internetUser);
    }
    
    public void update(InternetUser internetUser){
        internetUserDal = InternetUserDal.createAsSingleton();
        internetUserDal.update(internetUser);
    }
    
    public String whoIsActive(){
        Statement statement = null;
        dbHelper = DbHelper.createAsSingleton();
        Connection connection = null;
        ResultSet resultSet = null;
        String activity = null;
        String username = null;
        try {
            connection = dbHelper.getConnection();
            
            statement = connection.createStatement();
            String sql = "select username from internet_users where is_active = 'Yes'";
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
                username = resultSet.getString("username");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(InternetUserDal.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(InternetUserDal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return username;
    }
    
    public String queryFirstName(InternetUser internetUser) throws UniqueEntityException{
        internetUserDal = InternetUserDal.createAsSingleton();
        return internetUserDal.queryFirstName(internetUser);
    }
    
    public String queryLastName(InternetUser internetUser) throws UniqueEntityException{
        internetUserDal = InternetUserDal.createAsSingleton();
        return internetUserDal.queryLastName(internetUser);
    }
    
}
