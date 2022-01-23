package repository.entities;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DAO.util.HibernateSessionFactory;

import static org.junit.jupiter.api.Assertions.*;

class UserCredentialTest {
    UserCredential userCreds;

    @BeforeEach
    void setUp() {
        userCreds = new UserCredential();
    }

    @Test
    void testLogin() {
        Session session = null;
        Transaction transaction = null;
        try {
            userCreds.setUserID(1);
            session = HibernateSessionFactory.getSession();
            transaction = session.beginTransaction();
            userCreds = session.get(UserCredential.class, userCreds.getUserID());
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            Assertions.assertNotNull(userCreds);
        }
    }
}