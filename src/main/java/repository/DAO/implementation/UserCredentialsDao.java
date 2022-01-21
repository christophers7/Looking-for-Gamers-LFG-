package repository.DAO.implementation;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.DAO.interfaces.Loginable;
import repository.entities.UserCredential;

public class UserCredentialsDao implements Loginable {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Override
    public int createUser(UserCredential userCredential) {
        dLog.debug("Creating User in Database: " + userCredential);
        int userId = 0;
        try{

        }catch(HibernateException e){
//            if(tx != null)
//                if(!tx.isActive()) tx.rollback();
//            dLog.error(e.getMessage(), e);
        }
        if(userId != 0) {
            iLog.info("Successful creation of new User: " + userId + " " + userCredential);
            return userId;
        }else{
            return -1;
        }
    }

    @Override
    public UserCredential getUser(UserCredential userCredential) {
        return null;
    }

    @Override
    public void updateUser(UserCredential userCredential) {

    }

    @Override
    public void deleteUser(UserCredential userCredential) {

    }
}
