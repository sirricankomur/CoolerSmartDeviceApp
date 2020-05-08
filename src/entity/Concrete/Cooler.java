package entity.Concrete;

import entity.Abstract.IEntity;

public class Cooler implements IEntity{
    private int id;
    private String productId;
    private int centralProcessorUnitId;
    private int actuatorId;
    private int temperatureId;
    private String instantWorkingStatus;
    
    public Cooler(int id, String productId, int centralProcessorUnitId, int actuatorId, int temperatureId, String instantWorkingStatus) {
        this.id = id;
        this.productId = productId;
        this.centralProcessorUnitId = centralProcessorUnitId;
        this.actuatorId = actuatorId;
        this.temperatureId = temperatureId;
        this.instantWorkingStatus = instantWorkingStatus;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getProductId() {
        return productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public int getCentralProcessorUnitId() {
        return centralProcessorUnitId;
    }
    
    public void setCentralProcessorUnitId(int centralProcessorUnitId) {
        this.centralProcessorUnitId = centralProcessorUnitId;
    }
    
    public int getActuatorId() {
        return actuatorId;
    }
    
    public void setActuatorId(int actuatorId) {
        this.actuatorId = actuatorId;
    }
    
    public int getTemperatureId() {
        return temperatureId;
    }
    
    public void setTemperatureId(int temperatureId) {
        this.temperatureId = temperatureId;
    }
    
    public String getInstantWorkingStatus() {
        return instantWorkingStatus;
    }
    
    public void setInstantWorkingStatus(String instantWorkingStatus) {
        this.instantWorkingStatus = instantWorkingStatus;
    }
}
