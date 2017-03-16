package edu.matc.persistence;

import edu.matc.entity.Brand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by student on 3/14/17.
 */
public class BrandDaoTest {
    @Test
    public void getBrandTest() throws Exception {
        Brand brand = new BrandDao().getBrandTest(1);

        assertEquals("Test ", "Parkay", brand.getBrandName());
    }

}