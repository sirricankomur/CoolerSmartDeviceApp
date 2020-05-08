package dataAccess.Concrete;

import business.exception.UniqueEntityException;
import dataAccess.Abstract.IInternetUserDal;
import entity.Concrete.InternetUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class InternetUserDal implements IInternetUserDal{
    private static InternetUserDal internetUserDal;
    private DbHelper dbHelper;
    public InternetUserDal() {
    }
    
    public static InternetUserDal createAsSingleton() {
        if (internetUserDal == null) {
            internetUserDal = new InternetUserDal();
        }
        return internetUserDal;
    }
    
    @Override
    public void add(InternetUser internetUser){
        PreparedStatement preparedStatement = null;
        dbHelper = DbHelper.createAsSingleton();
        Connection connection = null;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "insert into internet_users (cooler_id, first_name,last_name,username,email,password_hash,is_active) values(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,internetUser.getCoolerId());
            preparedStatement.setString(2,internetUser.getFirstName());
            preparedStatement.setString(3,internetUser.getLastName());
            preparedStatement.setString(4,internetUser.getUsername());
            preparedStatement.setString(5,internetUser.getEmail());
            preparedStatement.setString(6,internetUser.getPasswordHash());
            preparedStatement.setString(7,internetUser.getIsActive());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InternetUserDal.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(InternetUserDal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void update(InternetUser internetUser){
        dbHelper = DbHelper.createAsSingleton();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dbHelper.getConnection();
            String sql = "update internet_users set is_active = ? where username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,internetUser.getIsActive());
            preparedStatement.setString(2,internetUser.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InternetUserDal.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(InternetUserDal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String queryFirstName(InternetUser internetUser) throws UniqueEntityException{
        dbHelper = DbHelper.createAsSingleton();
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String firstName = null;
        try {
            connection = dbHelper.getConnection();
            String sql = "select first_name from internet_users where username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, internetUser.getUsername());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                firstName = resultSet.getString("first_name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return firstName;
    }
    
    public String queryLastName(InternetUser internetUser) throws UniqueEntityException{
        dbHelper = DbHelper.createAsSingleton();
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String lastName = null;
        try {
            connection = dbHelper.getConnection();
            String sql = "select last_name from internet_users where username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, internetUser.getUsername());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                lastName = resultSet.getString("last_name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastName;
    }
    
}
