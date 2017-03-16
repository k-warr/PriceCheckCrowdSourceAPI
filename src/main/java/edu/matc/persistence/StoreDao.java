package edu.matc.persistence;

import edu.matc.entity.Store;
import org.apache.log4j.Logger;
import org.hibernate.*;
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

    public List<Integer> getNearestStore(double latitude, double longtitude,
                 double distance) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        double earthRadius = 3958.762079; //miles
        GeoLocation location = GeoLocation.fromRadians(latitude, longtitude);
        GeoLocation[] boundingCoordinates =
                location.boundingCoordinates(distance, earthRadius);
        boolean meridian180WithinDistance =
                boundingCoordinates[0].getLongitudeInRadians() >
                        boundingCoordinates[1].getLongitudeInRadians();
        String andOr = null;
        if (meridian180WithinDistance) {
            andOr = "OR";
        } else {
            andOr = "AND";
        }

        String hql = "SELECT storeId FROM Store WHERE (latitude >= :one "
                + "AND latitude <= :two) AND (longtitude >= :three "
                + andOr
                + " longtitude <= :four) AND acos(sin(:five) * sin(latitude) "
                + "+ cos(:six) * cos(latitude) * "
                + "cos(longitude - :seven)) <= :eight";

        Query query = session.createQuery(hql);
        query.setParameter("employee_id",10);

        query.setParameter("one", boundingCoordinates[0].getLatitudeInRadians());
        query.setParameter("two", boundingCoordinates[1].getLatitudeInRadians());
        query.setParameter("three", boundingCoordinates[0].getLongitudeInRadians());
        query.setParameter("four", boundingCoordinates[1].getLongitudeInRadians());
        query.setParameter("five", location.getLatitudeInRadians());
        query.setParameter("six", location.getLatitudeInRadians());
        query.setParameter("seven", location.getLongitudeInRadians());
        query.setParameter("eight", distance / earthRadius);
        List<Integer> results = query.list();

        return results;
    }
    
}
