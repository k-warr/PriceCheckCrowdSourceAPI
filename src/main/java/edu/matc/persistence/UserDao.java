package edu.matc.persistence;

import edu.matc.entity.User;
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
public class UserDao {
    private final Logger log = Logger.getLogger(this.getClass());


    /** Return a list of all users
     *
     * @return All users
     */
    public List<User> getAllUsers() {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> users = null;
        try {
            users = new ArrayList<User>();
            transaction = session.beginTransaction();
            users = session.createCriteria(User.class).list();
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        } finally {
            session.close();
        }
       return users;
    }



    /**
     * add a user expense
     *
     * @param user
     */
    public void addUser(User user) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
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
    public User getUser(int userId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        User user = null;
        try {
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, userId);
            transaction.commit();

        }catch (HibernateException hibernateException) {
            if (transaction!=null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return user;
    }

    /** Get user by name
     *
     * @param apiKey
     * @return userEntity
     */
    public List<Integer> getUserByName(String apiKey)  {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        List<Integer> userEntity = null;
        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.like("apiKey",apiKey));
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("userId"));
            criteria.setProjection(projectionList);
            userEntity = criteria.list();
        }catch (HibernateException hibernateException) {
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return userEntity;
    }

    /** Get user by API key
     *
     * @param apiKey
     * @return user
     * @throws Exception
     */
    public User getUserByApiKey(String apiKey) throws Exception {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        List<User> userEntity = null;
        Criteria criteria;
        try {
            criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("apiKey", apiKey));
            userEntity = criteria.list();
        } catch (HibernateException hibernateException) {
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return userEntity.get(0);
    }
}
