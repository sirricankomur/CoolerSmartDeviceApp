package entity.Concrete;

import entity.Abstract.IEntity;

public class TemperatureSensor implements IEntity{
    private int id;
    private int productId;
    private float measuredValue;
    private String isActive;
    
    public TemperatureSensor(int id, int productId, float measuredValue, String isActive) {
        this.id = id;
        this.productId = productId;
        this.measuredValue = measuredValue;
        this.isActive = isActive;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public float getMeasuredValue() {
        return measuredValue;
    }
    
    public void setMeasuredValue(float measuredValue) {
        this.measuredValue = measuredValue;
    }
    
    public String getIsActive() {
        return isActive;
    }
    
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
    
    
}
