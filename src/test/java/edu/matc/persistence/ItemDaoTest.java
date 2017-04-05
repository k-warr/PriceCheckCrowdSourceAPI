package edu.matc.persistence;

import edu.matc.entity.Item;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by student on 3/23/17.
 */
public class ItemDaoTest {

    ItemDao dao;

    @Before
    public void setUp() {
        dao = new ItemDao();
    }

    @Test
    public void testGetItemByName() throws Exception {
        List<Integer> itemEntity = null;
        itemEntity = dao.getItemByName("Ketchup");
        assertEquals(1, itemEntity.size());
    }

    @Ignore
    @Test
    public void testGetExactItem() throws Exception {
        List<Item> list = dao.getExactItem("Canned Butter", "ounce", 12);

        assertEquals("Should be equal but not ", 23, list.get(0).getItemId());

    }

    @Test
    public void testAddItem() throws Exception {
        int itemId = dao.addItem(new Item("<test>", "<test unit>", 99));

    }
}