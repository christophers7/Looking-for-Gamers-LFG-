package repository.entities.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.DAO.util.HibernateSessionFactory;
import repository.entities.UserProfile;

import java.util.List;

public class TestEntity {
List<UserProfile> testProfile (){
    Session session = null;
    Transaction transaction = null;
    List<UserProfile> userProfiles = null;
    try{
        session = HibernateSessionFactory.getSession();
        transaction = session.beginTransaction();
        userProfiles = session.createQuery("FROM UserProfile", UserProfile.class).getResultList();
        transaction.commit();
    }catch (HibernateException e ){
        transaction.rollback();
        e.printStackTrace();
    }
    return userProfiles;
}
}
