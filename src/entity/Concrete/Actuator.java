package entity.Concrete;

import entity.Abstract.IEntity;

public class Actuator implements IEntity{
    private int id;
    private int productId;
    private String isActive;
    
    public Actuator(int id, int productId, String isActive) {
        this.id = id;
        this.productId = productId;
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
    
    public String getIsActive() {
        return isActive;
    }
    
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
