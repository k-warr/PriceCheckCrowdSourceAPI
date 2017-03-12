package edu.matc.persistence;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Punitha Anandan on 3/11/2017.
 */
public class ItemDaoTest {
    ItemDao itemDao;

    @Before
    public void setup() {
        itemDao = new ItemDao();
    }

    @Test
    public void getItemEntity() throws Exception {
        ItemEntity itemEntity = itemDao.getBrandEntity(1);
        assertTrue(itemEntity.getItemName().equalsIgnoreCase("butter"));
    }
}
