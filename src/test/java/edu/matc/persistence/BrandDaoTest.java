package edu.matc.persistence;

import edu.matc.entity.Brand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by student on 4/6/17.
 */
public class BrandDaoTest {
    Brand brand;
    BrandDao dao;

    @Before
    public void setUp() throws Exception {
        brand =new Brand();
        dao = new BrandDao();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getExactBrand() throws Exception {
        List<Brand> brands = dao.getExactBrand("<none>");

        assertTrue("The brandID is > 0", brands.get(0).getBrandId() > 0);
    }

}