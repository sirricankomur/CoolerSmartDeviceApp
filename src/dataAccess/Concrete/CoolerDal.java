package dataAccess.Concrete;

import business.Concrete.ActuatorManager;
import business.Concrete.CentralProcessorUnitManager;
import business.Concrete.TemperatureSensorManager;
import business.exception.RecordNotFoundException;
import business.exception.UniqueEntityException;
import business.validationRules.CoolerProductIdValidator;
import dataAccess.Abstract.ICoolerDal;
import entity.Concrete.Actuator;
import entity.Concrete.CentralProcessorUnit;
import entity.Concrete.Cooler;
import entity.Concrete.TemperatureSensor;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoolerDal implements ICoolerDal{
    private static CoolerDal coolerDal;
    private DbHelper dbHelper;
    private CoolerProductIdValidator coolerProductIdValidator;
    private CentralProcessorUnitManager centralProcessorUnitManager;
    private ActuatorManager actuatorManager;
    private TemperatureSensorManager temperatureSensorManager;
    
    private CoolerDal() {
    }
    
    public static CoolerDal createAsSingleton() {
        if (coolerDal == null) {
            coolerDal = new CoolerDal();
        }
        return coolerDal;
    }
    
    @Override
    public void add(Cooler cooler,CentralProcessorUnit centralProcessorUnit, Actuator actuator,TemperatureSensor temperatureSensor) throws UniqueEntityException {
        dbHelper = DbHelper.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        centralProcessorUnitManager = CentralProcessorUnitManager.createAsSingleton();
        actuatorManager = ActuatorManager.createAsSingleton();
        temperatureSensorManager = TemperatureSensorManager.createAsSingleton();
        
        try {
            connection = dbHelper.getConnection();
            String sql = "insert into coolers (product_id,central_processor_unit_id,actuator_id,temperature_sensor_id,instant_working_status) values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cooler.getProductId());
            preparedStatement.setInt(2, centralProcessorUnitManager.whatIsTheId(centralProcessorUnit.getProductId()));
            preparedStatement.setInt(3, actuatorManager.whatIsTheId(actuator.getProductId()));
            preparedStatement.setInt(4, temperatureSensorManager.whatIsTheId(temperatureSensor.getProductId()));
            preparedStatement.setString(5, "Closed");
            
            if (queryProductId(cooler.getProductId()) == null) {
                System.out.println("The cooler has been successfully registered.");
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void updateInstantWorkingStatus(Cooler cooler){
        dbHelper = DbHelper.createAsSingleton();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "update coolers set instant_working_status = ? where product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,cooler.getInstantWorkingStatus());
            preparedStatement.setString(2,cooler.getProductId());
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
    public String queryStatusOfCooler(String enteredProductId) throws RecordNotFoundException{
        dbHelper = DbHelper.createAsSingleton();
        coolerProductIdValidator = CoolerProductIdValidator.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String instantWorkingStatus = null;
        
        try {
            connection = dbHelper.getConnection();
            
            String sql = "select instant_working_status from coolers where product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enteredProductId);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                instantWorkingStatus = resultSet.getString("instant_working_status");
            }
            coolerProductIdValidator.isThereProduct(instantWorkingStatus);
            
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instantWorkingStatus;
    }
    
    @Override
    public String queryProductId(String enteredProductId) throws UniqueEntityException{
        dbHelper = DbHelper.createAsSingleton();
        coolerProductIdValidator = CoolerProductIdValidator.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String productId = null;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "select product_id from coolers where product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enteredProductId);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                productId = resultSet.getString("product_id");
            }
            coolerProductIdValidator.isThereProductId(productId);
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productId;
    }
    
    @Override
    public int queryCoolerId(String enteredProductId) throws UniqueEntityException{
        dbHelper = DbHelper.createAsSingleton();
        coolerProductIdValidator = CoolerProductIdValidator.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = 0;
        String productId = null;
        Statement statement = null;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "select id from coolers where product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enteredProductId);
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
    public int queryCentralProcessorUnitId(String enteredProductId) throws UniqueEntityException{
        dbHelper = DbHelper.createAsSingleton();
        coolerProductIdValidator = CoolerProductIdValidator.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = 0;
        String productId = null;
        Statement statement = null;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "select central_processor_unit_id from coolers where product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enteredProductId);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                id = resultSet.getInt("central_processor_unit_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    @Override
    public int queryAcuatorId(String enteredProductId) throws UniqueEntityException{
        dbHelper = DbHelper.createAsSingleton();
        coolerProductIdValidator = CoolerProductIdValidator.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = 0;
        String productId = null;
        Statement statement = null;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "select actuator_id from coolers where product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enteredProductId);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                id = resultSet.getInt("actuator_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    @Override
    public int queryTemperatureSensorId(String enteredProductId) throws UniqueEntityException{
        dbHelper = DbHelper.createAsSingleton();
        coolerProductIdValidator = CoolerProductIdValidator.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = 0;
        String productId = null;
        Statement statement = null;
        try {
            connection = dbHelper.getConnection();
            String sql = "select temperature_sensor_id from coolers where product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enteredProductId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id = resultSet.getInt("temperature_sensor_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    @Override
    public String whatProductOfUser(String enteredUsername){
        dbHelper = DbHelper.createAsSingleton();
        coolerProductIdValidator = CoolerProductIdValidator.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int coolerId = 0;
        String productId = null;
        Statement statement = null;
        try {
            connection = dbHelper.getConnection();
            
            String sqlInternetUser = "select cooler_id from internet_users where username = ?";
            preparedStatement = connection.prepareStatement(sqlInternetUser);
            preparedStatement.setString(1, enteredUsername);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                coolerId = resultSet.getInt("cooler_id");
            }
            String sqlCoolers = "select product_id from coolers where id = ?";
            preparedStatement = connection.prepareStatement(sqlCoolers);
            preparedStatement.setInt(1, coolerId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                productId = resultSet.getString("product_id");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productId;
    }
    
    
}
