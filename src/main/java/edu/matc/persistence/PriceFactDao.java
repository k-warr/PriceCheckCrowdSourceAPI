package edu.matc.persistence;

import edu.matc.entity.PriceFact;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
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

    /**
     * add a user expense
     *
     * @param priceFact
     */

    public void addPriceFact(PriceFact priceFact) {
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

    public List<PriceFact> getItemPricex (String itemName, String brandName,
                                          double latitude,
                                          double longtitude, double radius) throws Exception {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(PriceFact.class);
        Criterion itemInList = null;
        Criterion storeInList = null;
        Criterion brandInList = null;

        List<PriceFact> priceFacts = null;

        List<Integer> itemList = new ItemDao().getItemByName(itemName);
        if (itemList.size() > 0) {
            itemInList = Restrictions.in("itemId",itemList);
        }

        List<Integer> storeList = new StoreDao().getNearestStoreId(latitude,
                longtitude, radius);
        if (storeList.size() > 0) {
            storeInList = Restrictions.in("storeId", storeList);
        }

        List<Integer> brandList = new BrandDao().getBrandByName(brandName);
        if (brandList.size() > 0) {
            brandInList = Restrictions.in("brandId", brandList);
        }

        if (itemList.size() > 0 && storeList.size() > 0 && brandList.size() >
                0) {
            criteria.add(itemInList);
            criteria.add(storeInList);
            criteria.add(brandInList);
        } else if (itemList.size() > 0 && storeList.size() > 0 && brandList.size() <= 0) {
            criteria.add(itemInList);
            criteria.add(storeInList);
        } else if (itemList.size() > 0 && storeList.size() <= 0 && brandList.size() > 0) {
            criteria.add(itemInList);
            criteria.add(brandInList);
        } else if (itemList.size() > 0) {
            criteria.add(itemInList);
        } else {
            return null;
        }

        priceFacts = criteria.list();
        session.close();

        return priceFacts;
    }

    public List<PriceFact> getItemPrice (String itemName, String itemUnit,
                                         int itemUnitValue, String brandName,
                                         List<Integer> storeIds) throws Exception {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteriaFact = session.createCriteria(PriceFact.class);
        Criteria criteriaItem = criteriaFact.createCriteria("itemByItemId");
        Criteria criteriaStore = criteriaFact.createCriteria("storeByStoreId");


        List<PriceFact> priceFacts = null;

        criteriaItem.add(Restrictions.ilike("itemName","%"+itemName+"%"));

        if (itemUnit!=null) {
            criteriaItem.add(Restrictions.eq("unit", itemUnit));
        }

        if (itemUnitValue > 0) {
            criteriaItem.add(Restrictions.eq("unitValue", itemUnitValue));
        }

        if (brandName!=null) {
            Criteria criteriaBrand = criteriaFact.createCriteria("brandByBrandId");
            criteriaBrand.add(Restrictions.eq("brandName", brandName));
        }

        criteriaStore.add(Restrictions.in("storeId", storeIds));

        criteriaFact.addOrder(Order.asc("storeId"));
        criteriaFact.addOrder(Order.asc("itemId"));
        criteriaFact.addOrder(Order.asc("brandId"));
        criteriaFact.addOrder(Order.desc("factDateTime"));
        criteriaFact.addOrder(Order.asc("priceAmount"));

        priceFacts = criteriaFact.list();
        session.close();

        return priceFacts;
    }

}
