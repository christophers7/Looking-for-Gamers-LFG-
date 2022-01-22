package repository.DAO.implementation;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.DAO.interfaces.Loginable;
import repository.DAO.util.HibernateSessionFactory;
import repository.entities.UserCredential;

public class UserCredentialsDao implements Loginable {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Override
    public int createUser(UserCredential userCredential) {
        dLog.debug("Creating User in Database: " + userCredential);
        int userID = 0;
        Session session = null;
    	Transaction transaction = null;
        try{
        	session = HibernateSessionFactory.getSession();
        	transaction = session.beginTransaction();
        	userID = (Integer) session.save(userCredential);
        	transaction.commit();
        }catch(HibernateException e){
        	 if(transaction != null)
                 if(!transaction.isActive()) transaction.rollback();
             dLog.error(e.getMessage(), e);
        }
        if(userID != 0) {
            iLog.info("Successful creation of new User: " + userID + " " + userCredential);
            return userID;
        }else{
            return -1;
        }
    }

    @Override
    public UserCredential getUser(UserCredential userCredential) {
    	
    	Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			userCredential = session.get(UserCredential.class, userCredential.getUserID());
			transaction.commit();
		}catch(HibernateException e) {
			 if(transaction != null)
                 if(!transaction.isActive()) transaction.rollback();
             dLog.error(e.getMessage(), e);
        }
			
			return userCredential;
    }

    @Override
    public void updateUser(UserCredential userCredential) {
    	
    	Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.update(userCredential);
			transaction.commit();
		}catch(HibernateException e) {
			if(transaction != null)
                if(!transaction.isActive()) transaction.rollback();
            dLog.error(e.getMessage(), e);
       }
		

    }

    @Override
    public void deleteUser(UserCredential userCredential) {

    	Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.delete(userCredential.getUserLogin(), UserCredential.class);
			transaction.commit();
		}catch(HibernateException e) {
			if(transaction != null)
                if(!transaction.isActive()) transaction.rollback();
            dLog.error(e.getMessage(), e);
       }
		
    }
}
