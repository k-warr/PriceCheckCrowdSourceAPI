package edu.matc.persistence;

import edu.matc.entity.Store;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Punitha Anandan on 3/11/2017.
 */
public class StoreDao {
    private final Logger log = Logger.getLogger(this.getClass());


    /**
     * add a user expense
     *
     * @param store
     */
    public void addStore(Store store) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(store);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        } finally {
            session.close();
        }
    }


    /** Get a store for given expenseName
     *
     * @param storeId  The name of Expense
     * @return store
     */
    public Store getStore(int storeId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        Store store = null;
        try {
            transaction = session.beginTransaction();
            store = (Store) session.get(Store.class, storeId);
            transaction.commit();

        }catch (HibernateException hibernateException) {
            if (transaction!=null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return store;
    }
    
    public List<Integer> getStoreByName(String storeName) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        List<Integer> storeEntity = null;
        try {
            Criteria criteria = session.createCriteria(Store.class);
            criteria.add(Restrictions.like("storeName",storeName));
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("storeId"));
            criteria.setProjection(projectionList);
            storeEntity = criteria.list();
        }catch (HibernateException hibernateException) {
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return storeEntity;
    }
    
}
