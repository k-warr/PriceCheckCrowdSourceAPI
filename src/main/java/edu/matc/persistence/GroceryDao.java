/*
package edu.matc.persistence;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

*/
/**
 * Created by Punitha Anandan on 3/11/2017.
 *//*

public class GroceryDao {
    private final Logger log = Logger.getLogger(this.getClass());


    */
/**
     * add a user expense
     *
     * @param groceryEntity
     *//*

    public void addBrand(GroceryEntity groceryEntity) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(groceryEntity);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        } finally {
            session.close();
        }
    }


    */
/** Get a grocery for given expenseName
     *
     * @param groceryId  The name of Expense
     * @return groceryEntity
     *//*

    public GroceryEntity getGroceryEntity(int groceryId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        GroceryEntity groceryEntity = null;
        try {
            transaction = session.beginTransaction();
            groceryEntity = (GroceryEntity) session.get(GroceryEntity.class, groceryId);
            transaction.commit();

        }catch (HibernateException hibernateException) {
            if (transaction!=null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return groceryEntity;
    }
}
*/
