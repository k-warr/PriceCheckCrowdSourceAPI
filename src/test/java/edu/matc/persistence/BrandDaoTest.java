package edu.matc.persistence;

import edu.matc.entity.Brand;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Punitha Anandan on 4/7/2017.
 */
public class BrandDaoTest {
    Brand brand;
    BrandDao brandDao;

    @Before
    public void setUp() {
        brandDao = new BrandDao();
        brand = new Brand();
    }


    @Test
    public void addBrand() throws Exception {

        brand.setBrandName("punitha");
        brandDao.addBrand(brand);
    }

    @Test
    public void testgetBrand() throws Exception {
        brand = brandDao.getBrand(26);
        assertTrue(brand.getBrandId()==26);
    }



    @Test
    public void getExactBrand() throws Exception {
        List<Brand> brands = brandDao.getExactBrand("<none>");

        assertTrue("The brandID is > 0", brands.get(0).getBrandId() > 0);
    }

}