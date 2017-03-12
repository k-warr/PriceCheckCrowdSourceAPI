package edu.matc.persistence;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Punitha Anandan on 3/11/2017.
 */
public class ItemDao {
    private final Logger log = Logger.getLogger(this.getClass());


    /**
     * add a user expense
     *
     * @param itemEntity
     */
    public void addBrand(ItemEntity itemEntity) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(itemEntity);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        } finally {
            session.close();
        }
    }

    /** Get a item for given brandId
     *
     * @param itemId  The name of Expense
     * @return itemEntity
     */
    public ItemEntity getBrandEntity(int itemId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        ItemEntity itemEntity = null;
        try {
            transaction = session.beginTransaction();
            itemEntity = (ItemEntity) session.get(ItemEntity.class, itemId);
            transaction.commit();

        }catch (HibernateException hibernateException) {
            if (transaction!=null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return itemEntity;
    }
}
