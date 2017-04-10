package edu.matc.persistence;

import edu.matc.entity.PriceFact;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

/**
 * Created by student on 3/19/17.
 */
public class PriceFactDaoTest {
    private final Logger log = Logger.getLogger(this.getClass());

    @Test
    public void getItemPricexTest() throws Exception {
        System.out.println("");
        List<PriceFact> results = new PriceFactDao().getItemPricex("Ketchup", "Hunt's", 43.183093000,
                -89.213428000, 10);
        int counter = 0;
        if (results.size() != 0) {
            for (PriceFact pricefact : results) {
                log.info("Num of Results: " + results.size());
                log.info(counter + "----- :" + pricefact.getPriceAmount());
                counter++;
            }
        } else {
            log.info("NULL");
        }

        log.info("TESTTTT");

    }


}

