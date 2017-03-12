package edu.matc.persistence;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Punitha Anandan on 3/11/2017.
 */
public class BrandDaoTest {
    BrandDao brandDao;

    @Before
    public void setup() {
        brandDao = new BrandDao();
    }

    @Test
    public void getBrandEntity() throws Exception {
        BrandEntity brandEntity = brandDao.getBrandEntity(1);
        assertTrue(brandEntity.getBrandName().equalsIgnoreCase("Gif"));
    }
}
