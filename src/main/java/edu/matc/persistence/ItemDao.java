package edu.matc.persistence;

import edu.matc.entity.Item;
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
public class ItemDao {
    private final Logger log = Logger.getLogger(this.getClass());


    /**
     * add a user expense
     *
     * @param item
     */
    public void addItem(Item item) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            if (transaction != null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        } finally {
            session.close();
        }
    }

    /** Get a item for given itemId
     *
     * @param itemId  The name of Expense
     * @return item
     */
    public Item getItemEntity(int itemId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = null;
        Item item = null;
        try {
            transaction = session.beginTransaction();
            item = (Item) session.get(Item.class, itemId);
            transaction.commit();

        }catch (HibernateException hibernateException) {
            if (transaction!=null) transaction.rollback();
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return item;
    }
    public List<Integer> getItemByName(String itemName) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        List<Integer> itemEntity = null;
        try {
            Criteria criteria = session.createCriteria(Item.class);
            criteria.add(Restrictions.like("itemName",itemName));
            ProjectionList projectionList = Projections.projectionList();
            projectionList.add(Projections.property("itemId"));
            criteria.setProjection(projectionList);
            itemEntity = criteria.list();
        }catch (HibernateException hibernateException) {
            log.error("Hibernate Exception", hibernateException);
        }finally {
            session.close();
        }
        return itemEntity;
    }

    public List<Integer> getItemByMultipleFieldsId(String name, String unit, int
            unitValue) {
        List<Integer> itemIds = null;

        for (Item item: getItemByMultipleFields(name, unit, unitValue)) {
            itemIds.add(item.getItemId());
        }

        return itemIds;
    }

    public List<Item> getItemByMultipleFields(String name, String unit, int
            unitValue) {

        List<Item> itemList = null;

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Item.class);
        criteria.add(Restrictions.like("itemName",name));

        if (!unit.equals(null) || !unit.equals("") || !unit.equals(" ")) {
            criteria.add(Restrictions.eq("unit", unit));
        }

        if (unitValue > 0) {
            criteria.add(Restrictions.eq("unitValue", unitValue));
        }

        itemList =  criteria.list();
        session.close();

        return itemList;
    }

}
