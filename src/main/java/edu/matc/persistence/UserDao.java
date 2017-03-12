package edu.matc.persistence;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Punitha Anandan on 3/11/2017.
 */
public class UserDao {
    private final Logger log = Logger.getLogger(this.getClass());


    /**
     * add a user expense
     *
     * @param userEntity
     */
    public void addBrand(UserEntity userEntity) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(userEntity);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        } finally {
            session.close();
        }
    }


    /** Get a pricefact for given brandId
     *
     * @param userId  The name of Expense
     * @return priceFactEntity
     */
    public UserEntity getUserEntity(long userId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        UserEntity userEntity = null;
        try {
            transaction = session.beginTransaction();
            userEntity = (UserEntity) session.get(UserEntity.class, userId);
            transaction.commit();

        }catch (HibernateException hibernateException) {
            if (transaction!=null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return userEntity;
    }
}
