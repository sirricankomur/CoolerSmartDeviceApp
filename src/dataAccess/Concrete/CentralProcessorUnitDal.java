package dataAccess.Concrete;

import business.exception.UniqueEntityException;
import business.validationRules.CentralProcessorUnitValidator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dataAccess.Abstract.ICentralProcessorUnitDal;
import entity.Concrete.CentralProcessorUnit;
import entity.Concrete.Cooler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CentralProcessorUnitDal implements ICentralProcessorUnitDal{
    private static CentralProcessorUnitDal centralProcessorUnitDal;
    private CentralProcessorUnitValidator centralProcessorUnitValidator;
    private DbHelper dbHelper;
    
    private CentralProcessorUnitDal() {
    }
    
    public static CentralProcessorUnitDal createAsSingleton() {
        if (centralProcessorUnitDal == null) {
            centralProcessorUnitDal = new CentralProcessorUnitDal();
        }
        return centralProcessorUnitDal;
    }
    
    @Override
    public void add(CentralProcessorUnit centralProcessorUnit) throws UniqueEntityException {
        dbHelper = DbHelper.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "insert into central_processor_units (product_id, is_active) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, centralProcessorUnit.getProductId());
            preparedStatement.setString(2, "No");
            if (queryProductId(centralProcessorUnit.getProductId()) == 0) {
                System.out.println("The central has been successfully registered.");
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void turnOnCentralProcessorUnit(Cooler cooler){
        dbHelper = DbHelper.createAsSingleton();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "update central_processor_units set is_active = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"Yes");
            preparedStatement.setInt(2,cooler.getCentralProcessorUnitId());
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
    public void turnOffCentralProcessorUnit(Cooler cooler){
        dbHelper = DbHelper.createAsSingleton();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "update central_processor_units set is_active = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"No");
            preparedStatement.setInt(2,cooler.getCentralProcessorUnitId());
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
    public void sleepOnCooler(Cooler cooler){
        dbHelper = DbHelper.createAsSingleton();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "update central_processor_units set is_active = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"Yes");
            preparedStatement.setInt(2,cooler.getCentralProcessorUnitId());
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
    public void sleepOffCooler(Cooler cooler){
        dbHelper = DbHelper.createAsSingleton();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "update central_processor_units set is_active = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"No");
            preparedStatement.setInt(2,cooler.getCentralProcessorUnitId());
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
        centralProcessorUnitValidator = CentralProcessorUnitValidator.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = 0;
        try {
            connection = dbHelper.getConnection();
            
            String sql = "select id from central_processor_units where product_id = ?";
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
        centralProcessorUnitValidator = CentralProcessorUnitValidator.createAsSingleton();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int productId = 0;
        try {
            connection = dbHelper.getConnection();
            String sql = "select product_id from central_processor_units where product_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, enteredProductId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                productId = resultSet.getInt("product_id");
            }
            centralProcessorUnitValidator.isThereProductId(productId);
        } catch (SQLException ex) {
            Logger.getLogger(CoolerDal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productId;
    }
}
