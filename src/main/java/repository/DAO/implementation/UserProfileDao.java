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
import repository.DAO.interfaces.Profileable;
import repository.DAO.util.HibernateSessionFactory;
import repository.entities.UserCredential;
import repository.entities.UserProfile;

public class UserProfileDao implements Profileable {

    private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    @Override
    public int createProfile(UserProfile profile) {
    	 dLog.debug("Creating User in Database: " + profile);
         int columnID = 0;
         Session session = null;
     	Transaction transaction = null;
         try{
         	session = HibernateSessionFactory.getSession();
         	transaction = session.beginTransaction();
         	session.save(profile);
         	transaction.commit();
         }catch(HibernateException e){
         	 if(transaction != null)
                  if(!transaction.isActive()) transaction.rollback();
              dLog.error(e.getMessage(), e);
         }
         if(columnID != 0) {
             iLog.info("Successful creation of new Profile: " + columnID + " " + profile);
             return columnID;
         }else{
             return -1;
         }
    	
    }

    @Override
    public UserProfile getUserProfile(UserProfile profile) {
    	
    	Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			profile = session.get(UserProfile.class, profile.getColumnID());
			transaction.commit();
		}catch(HibernateException e) {
			 if(transaction != null)
                 if(!transaction.isActive()) transaction.rollback();
             dLog.error(e.getMessage(), e);
        }
			
			return profile;
    }

    @Override
    public void updateUserProfile(UserProfile profile) {

    	Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.update(profile);
			transaction.commit();
		}catch(HibernateException e) {
			if(transaction != null)
                if(!transaction.isActive()) transaction.rollback();
            dLog.error(e.getMessage(), e);
       }
    }

    @Override
    public void deleteUserProfile(UserProfile profile) {
    	
    	Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.delete(profile.getEmail(), UserProfile.class);
			transaction.commit();
		}catch(HibernateException e) {
			if(transaction != null)
                if(!transaction.isActive()) transaction.rollback();
            dLog.error(e.getMessage(), e);
       }
    }

    @Override
    public UserProfile getUserProfileWithUserCredentials(UserCredential userCredential) {
    	dLog.debug("Searching for Profile in Database: " + userCredential);
    	UserProfile profile = null;
    	Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaQuery<UserProfile> cq = cb.createQuery(UserProfile.class);
//			Root<UserProfile> root = cq.from(UserProfile.class);
//			cq.select(root).where(cb.equal(root.get("Username"), userCredential.getUserID()));
//			Query<UserProfile> query = session.createQuery(cq);
//			profile = query.getSingleResult();
			profile = session.createQuery("FROM UserProfile u WHERE u.userCredential.userID = :user",UserProfile.class)
							.setParameter("user", userCredential.getUserID()).getSingleResult();

			transaction.commit();
    	
    }catch(HibernateException e) {
		if(transaction != null)
            if(!transaction.isActive()) transaction.rollback();
        dLog.error(e.getMessage(), e);
	} 
		return profile;
    }
}
