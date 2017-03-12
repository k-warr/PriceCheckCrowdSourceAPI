package edu.matc.persistence;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Punitha Anandan on 3/11/2017.
 */
public class PriceFactDao {
    private final Logger log = Logger.getLogger(this.getClass());


    /**
     * add a user expense
     *
     * @param priceFactEntity
     */
    public void addBrand(PriceFactEntity priceFactEntity) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(priceFactEntity);
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
     * @param factId  The name of Expense
     * @return priceFactEntity
     */
    public PriceFactEntity getPriceFactEntity(long factId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        PriceFactEntity priceFactEntity = null;
        try {
            transaction = session.beginTransaction();
            priceFactEntity = (PriceFactEntity) session.get(PriceFactEntity.class, factId);
            transaction.commit();

        }catch (HibernateException hibernateException) {
            if (transaction!=null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return priceFactEntity;
    }
}
