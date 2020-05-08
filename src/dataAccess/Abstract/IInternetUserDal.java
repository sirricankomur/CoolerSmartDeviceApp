package dataAccess.Abstract;

import business.exception.UniqueEntityException;
import entity.Concrete.InternetUser;
import java.sql.SQLException;

public interface IInternetUserDal {
    void add(InternetUser internetUser) throws SQLException;
    void update(InternetUser internetUser);    
    String queryFirstName(InternetUser internetUser) throws UniqueEntityException;
    String queryLastName(InternetUser internetUser) throws UniqueEntityException;
}
