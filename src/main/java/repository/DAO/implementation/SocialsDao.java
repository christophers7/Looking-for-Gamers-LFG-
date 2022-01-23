package repository.DAO.implementation;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import repository.DAO.interfaces.Sociable;
import repository.DAO.util.HibernateSessionFactory;
import repository.entities.Socials;

public class SocialsDao implements Sociable{
	
	private final Logger iLog = LoggerFactory.getLogger("iLog");
    private final Logger dLog = LoggerFactory.getLogger("dLog");
    
	@Override
	public Socials findPlatform(Socials platform) {
		dLog.debug("Searching for Platforms: " + platform);
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			platform = session.get(Socials.class, platform.getPlatform());
			transaction.commit();
		}catch(HibernateException e) {
			 if(transaction != null)
                 if(!transaction.isActive()) transaction.rollback();
             dLog.error(e.getMessage(), e);
        }
			
			return platform;
		
	}

}
