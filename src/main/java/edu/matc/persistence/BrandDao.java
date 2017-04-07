package edu.matc.persistence;

import edu.matc.entity.Brand;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

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
    public int addBrand(Brand brandEntity) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        int brandId = 0;

        try {
            transaction = session.beginTransaction();
            brandId = (Integer) session.save(brandEntity);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        } finally {
            session.close();
        }
        return brandId;
    }


    /** Return a list of all brand
     *
     * @return All brand
     */
    public List<Brand> getAllBrand() {
        List<Brand> brands = new ArrayList<Brand>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            brands = session.createCriteria(Brand.class).list();
            transaction.commit();
        }catch (HibernateException hibernateException) {
            if (transaction!=null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return brands;
    }


    /** Get a brand for given brandId
     *
     * @param brandId  Brand Id
     * @return brand
     */
    public Brand getBrand(int brandId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        Brand brandEntity = null;
        try {
            transaction = session.beginTransaction();
            brandEntity = (Brand) session.get(Brand.class, brandId);
            transaction.commit();

        }catch (HibernateException hibernateException) {
            if (transaction!=null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return brandEntity;
    }

    /**Get brand by name
     *
     * @param brandName
     * @return brandEntity
     */
    public List<Integer> getBrandByName(String brandName) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        List<Integer> brandEntity = null;
        Transaction transaction = null;
        Criteria criteria;
        try {
            criteria = session.createCriteria(Brand.class);
            criteria.add(Restrictions.like("brandName",brandName));
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("brandId"));
            criteria.setProjection(projectionList);

            brandEntity = criteria.list();
        }catch (HibernateException hibernateException) {
            if (transaction!=null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return brandEntity;
    }

    /**Get exact brand
     *
     * @param brandName
     * @return brandEntity
     */
    public List<Brand> getExactBrand(String brandName) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        List<Brand> brandEntity = null;
        Transaction transaction = null;
        Criteria criteria;
        try {
            criteria = session.createCriteria(Brand.class);
            criteria.add(Restrictions.eq("brandName",brandName));
            brandEntity = criteria.list();
        }catch (HibernateException hibernateException) {
            if (transaction!=null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return brandEntity;
    }

}
