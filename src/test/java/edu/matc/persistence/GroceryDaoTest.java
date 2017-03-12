package edu.matc.persistence;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Punitha Anandan on 3/11/2017.
 */
public class GroceryDaoTest {
    GroceryDao groceryDao;

    @Before
    public void setup() {
        groceryDao = new GroceryDao();
    }

    @Test
    public void getBrandEntity() throws Exception {
        GroceryEntity groceryEntity = groceryDao.getGroceryEntity(1);
        assertTrue(groceryEntity.getGroceryName().equalsIgnoreCase("butter"));
    }
}
