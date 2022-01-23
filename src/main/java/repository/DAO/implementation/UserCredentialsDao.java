package repository.DAO.implementation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
    	dLog.debug("Getting User in Database: " + userCredential);
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
    	dLog.debug("Updating User in Database: " + userCredential);
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
    	dLog.debug("Deleting User from Database: " + userCredential);
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

    @Override
    public UserCredential getUserWithUsername(UserCredential userCredential) {
    	dLog.debug("Searching for User in Database: " + userCredential);
    	UserCredential username = null;
    	Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<UserCredential> cq = cb.createQuery(UserCredential.class);
			Root<UserCredential> root = cq.from(UserCredential.class);
			cq.select(root).where(cb.equal(root.get("Username"), userCredential.getUserLogin()));
			Query<UserCredential> query = session.createQuery(cq);
			userCredential = query.getSingleResult();
			transaction.commit();
    	
    }catch(HibernateException e) {
		if(transaction != null)
            if(!transaction.isActive()) transaction.rollback();
        dLog.error(e.getMessage(), e);
	} 
		return username;
    }
}
