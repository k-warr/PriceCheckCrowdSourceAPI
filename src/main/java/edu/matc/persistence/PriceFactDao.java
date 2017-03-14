package edu.matc.persistence;

import edu.matc.entity.PriceFact;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Punitha Anandan on 3/11/2017.
 */
public class PriceFactDao {
    private final Logger log = Logger.getLogger(this.getClass());


    /**
     * add a user expense
     *
     * @param priceFact
     */
    public void addBrand(PriceFact priceFact) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(priceFact);
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
     * @return priceFact
     */
    public PriceFact getPriceFact(long factId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        PriceFact priceFact = null;
        try {
            transaction = session.beginTransaction();
            priceFact = (PriceFact) session.get(PriceFact.class, factId);
            transaction.commit();

        }catch (HibernateException hibernateException) {
            if (transaction!=null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return priceFact;
    }

    public List<PriceFact> getItemPrice(String itemName, String storeName,
                Long latitude, Long longtitude, int radius) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        List<PriceFact> priceFacts = null;
        try {
            Criteria criteria = session.createCriteria(PriceFact.class);
            criteria.add(Restrictions.in("itemId",new ItemDao().getItemByName(itemName)));
            criteria.add(Restrictions.in("storeId",new StoreDao().getStoreByName(storeName)));
            priceFacts = criteria.list();
        }catch (HibernateException hibernateException) {
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return priceFacts;
    }
}
