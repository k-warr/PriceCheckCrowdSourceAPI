package edu.matc.persistence;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Punitha Anandan on 3/11/2017.
 */
public class PriceFactDaoTest {
    PriceFactDao priceFactDao;

    @Before
    public void setup() {
        priceFactDao = new PriceFactDao();
    }

    @Test
    public void getPriceFactEntity() throws Exception {
        PriceFactEntity priceFactEntity = priceFactDao.getPriceFactEntity(1);
        assertTrue(priceFactEntity.getPriceAmount().doubleValue()>0);
    }
}
