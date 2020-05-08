package entity.Concrete;

import entity.Abstract.IEntity;

public class InternetUser implements IEntity {
    private int id;
    private int coolerId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String passwordHash;
    private String isActive;
    
    
    public InternetUser(int id, int coolerId, String firstName, String lastName, String username, String email, String passwordHash, String isActive){
        this.id = id;
        this.coolerId = coolerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.isActive = isActive;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getCoolerId() {
        return coolerId;
    }
    
    public void setCoolerId(int coolersId) {
        this.coolerId = coolersId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPasswordHash() {
        return passwordHash;
    }
    
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    
    public String getIsActive() {
        return isActive;
    }
    
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
    
    
    
    
}
