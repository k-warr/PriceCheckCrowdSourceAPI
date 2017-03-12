package edu.matc.persistence;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Punitha Anandan on 3/11/2017.
 */
public class BrandDao {
    private final Logger log = Logger.getLogger(this.getClass());


    /**
     * add a Brand
     *
     * @param brandEntity
     */
    public void addBrand(BrandEntity brandEntity) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(brandEntity);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        } finally {
            session.close();
        }
    }


    /** Get a brand for given brandId
     *
     * @param brandId  The name of Expense
     * @return brandEntity
     */
    public BrandEntity getBrandEntity(int brandId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        BrandEntity brandEntity = null;
        try {
            transaction = session.beginTransaction();
            brandEntity = (BrandEntity) session.get(BrandEntity.class, brandId);
            transaction.commit();

        }catch (HibernateException hibernateException) {
            if (transaction!=null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return brandEntity;
    }
}
