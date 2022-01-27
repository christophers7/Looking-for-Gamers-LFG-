package com.revature.p2_lfg.repository.DAO.implementation;

import com.revature.p2_lfg.repository.DAO.interfaces.Sociable;
import com.revature.p2_lfg.repository.DAO.util.HibernateSessionFactory;
import com.revature.p2_lfg.repository.entities.Socials;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("socialsDao")
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
			platform = session.get(Socials.class, platform.getGamerTag());
			transaction.commit();
		}catch(HibernateException e) {
			 if(transaction != null)
                 if(!transaction.isActive()) transaction.rollback();
             dLog.error(e.getMessage(), e);
        }
			
			return platform;
		
	}

}
