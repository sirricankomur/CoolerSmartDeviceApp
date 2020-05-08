package business.observer;

public class InternetUserObserver extends Observer{

    @Override
    public void update() {
        System.out.println("The cooler has been updated."); 
    }
    
}
