package dataAccess.Concrete;

import java.sql.*;
import dataAccess.Abstract.ITemperatureSensorDal;
import entity.Concrete.TemperatureSensor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TemperatureSensorDal implements ITemperatureSensorDal{
    private static TemperatureSensorDal temperatureSensorDal;
    private DbHelper dbHelper;
    
    private TemperatureSensorDal() {
    }
    
    public static TemperatureSensorDal createAsSingleton() {
        if (temperatureSensorDal == null) {
            temperatureSensorDal = new TemperatureSensorDal();
        }
        return temperatureSensorDal;
    }
    
    @Override
    public void add(TemperatureSensor temperatureSensor){
        dbHelper = DbHelper.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "insert into temperature_sensors (product_id, measured_value,is_active) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, temperatureSensor.getProductId());
            preparedStatement.setFloat(2, 0);
            preparedStatement.setString(3, "No");
            
            if (queryProductId(temperatureSensor.getProductId()) == 0) {
                System.out.println("The temperature has been successfully registered.");
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update(TemperatureSensor temperatureSensor){
        dbHelper = DbHelper.createAsSingleton();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "update temperature_sensors set measured_value = ?, is_active = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1,temperatureSensor.getMeasuredValue());
            preparedStatement.setString(2,temperatureSensor.getIsActive());
            preparedStatement.setInt(3,temperatureSensor.getId());
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = 0;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "select id from temperature_sensors where product_id = ?";
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
    public int queryProductId(int enteredProductId){
        dbHelper = DbHelper.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int productId = 0;
        
        try {
            connection = dbHelper.getConnection();
            
            String sql = "select product_id from temperature_sensors where product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, enteredProductId);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                productId = resultSet.getInt("product_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productId;
    }
    
    @Override
    public void queryLastTemperatureValue(TemperatureSensor temperatureSensor){
        dbHelper = DbHelper.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = dbHelper.getConnection();
            
            String sql = "select measured_value from temperature_sensors where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, temperatureSensor.getId());
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                temperatureSensor.setMeasuredValue(resultSet.getFloat("measured_value"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
}
