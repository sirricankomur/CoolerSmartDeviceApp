package dataAccess.Concrete;

import business.Concrete.CentralProcessorUnitManager;
import java.sql.*;
import business.exception.UniqueEntityException;
import business.validationRules.ActuatorValidator;
import dataAccess.Abstract.IActuatorDal;
import entity.Concrete.Actuator;
import entity.Concrete.Cooler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActuatorDal implements IActuatorDal{
    private static ActuatorDal actuatorDal;
    private DbHelper dbHelper;
    private ActuatorValidator actuatorValidator;
    CentralProcessorUnitManager centralProcessorUnitManager;
    
    private ActuatorDal() {
    }
    
    public static ActuatorDal createAsSingleton() {
        if (actuatorDal == null) {
            actuatorDal = new ActuatorDal();
        }
        return actuatorDal;
    }
    
    @Override
    public void add(Actuator actuator) throws UniqueEntityException {
        dbHelper = DbHelper.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dbHelper.getConnection();
            String sql = "insert into actuators (product_id, is_active) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, actuator.getProductId());
            preparedStatement.setString(2, actuator.getIsActive());
            if (queryProductId(actuator.getProductId()) == 0) {
                System.out.println("The actuator has been successfully registered.");
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void turnOnActuator(Cooler cooler){
        dbHelper = DbHelper.createAsSingleton();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dbHelper.getConnection();
            String sql = "update actuators set is_active = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"Yes");
            preparedStatement.setInt(2,cooler.getActuatorId());
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
    public void turnOffActuator(Cooler cooler){
        dbHelper = DbHelper.createAsSingleton();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dbHelper.getConnection();
            String sql = "update actuators set is_active = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"No");
            preparedStatement.setInt(2,cooler.getActuatorId());
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
    public int whatIsTheId(int enteredProductId){
        dbHelper = DbHelper.createAsSingleton();
        actuatorValidator = ActuatorValidator.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = 0;
        try {
            connection = dbHelper.getConnection();
            String sql = "select id from actuators where product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, enteredProductId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    @Override
     public int queryProductId(int enteredProductId) throws UniqueEntityException{
        dbHelper = DbHelper.createAsSingleton();
        actuatorValidator = ActuatorValidator.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int productId = 0;
        try {
            connection = dbHelper.getConnection();
            String sql = "select product_id from actuators where product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, enteredProductId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                productId = resultSet.getInt("product_id");
            }
            actuatorValidator.isThereProductId(productId);
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productId;
    }
}
