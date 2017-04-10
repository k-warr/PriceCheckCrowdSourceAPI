package edu.matc.pricecheck;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


/**
 * Created by student on 3/23/17.
 */

public class PriceRequestTest {



    PriceRequest priceRequest;
    Response response;

    @Before
    public void setup() {

        priceRequest = new PriceRequest();

    }




    @Test
    public void addItemJSON() throws Exception {
        response = priceRequest.addItemJSON("<testItem>",99.99,
                "<testItemUnit>", "99", "<testBrandName",
               "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system");
        assertEquals("Return code should be 200", 200, response
                .getStatus());
        assertEquals("Message expected is not met", "{\"message\" : \"200 : Added Successfully!\"}" , response.getEntity().toString());
    }

    @Test
    public void addItemHTML() throws Exception {
        response = priceRequest.addItemHTML("<testItem>",99.99,
                "<testItemUnit>", "99", "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system");
        assertEquals("Return code should be 200", 200, response
                .getStatus());
        assertEquals("Message expected is not met", "<h2><span>message:</span>200 : Added Successfully!</h2>" , response.getEntity().toString());
    }


    @Test
    public void addNewUserJSON() throws Exception {
        response = priceRequest.addNewUserJSON();
        assertEquals("Return code should be 200", 200, response
                .getStatus());
        assertEquals("Message expected is not met", "{\"apiKey\" : ",
                response.getEntity().toString().substring(0,12));
    }

    @Test
    public void addNewUserHTML() throws Exception {
        response = priceRequest.addNewUserHTML();
        assertEquals("Return code should be 200", 200, response
                .getStatus());
        assertEquals("Message expected is not met",
                "<h2><span>apiKey:</span>",
                response.getEntity().toString().substring(0,24));
    }

    @Test
    public void getMsgPlainJSON() throws Exception {
        response = priceRequest.addItemHTML("<testItem>",99.99,
                "<testItemUnit>", "99", "<testBrandName>",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system");
        response = priceRequest.getMsgPlainJSON("<testItem>", "<testBrandName>", 0.000000, 0.000000, 1);

        assertEquals("Return code should be 200", 200, response.getStatus());
    }

    @Test
    public void getMsgHTML() throws Exception {
        response = priceRequest.addItemHTML("<testItem>",99.99,
                "<testItemUnit>", "99", "<testBrandName>",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system");
        response = priceRequest.getMsgPlainJSON("<testItem>", "<testBrandName>", 0.000000, 0.000000, 1);

        assertEquals("Return code should be 200", 200, response.getStatus());
    }


    @Test
    public void testGetAllItems() throws Exception {
        response = priceRequest.getAllItems();
        assertTrue(response.getStatus() > 0);


    }

    @Test
    public void testGetAllBrand() throws Exception {
        response = priceRequest.getAllBrand();
        assertTrue(response.getStatus() > 0);
    }

    @Test
    public void testGetAllStores() throws Exception {
        response = priceRequest.getAllStores();
        assertTrue(response.getStatus() > 0);
    }
    @Test
    public void testGetAllItemsHTML() throws Exception {
        response = priceRequest.getAllItemsHTML();
        assertTrue(response.getStatus() > 0);


    }

    @Test
    public void testGetAllBrandHTML() throws Exception {
        response = priceRequest.getAllBrandsHTML();
        assertTrue(response.getStatus() > 0);
    }

    @Test
    public void testGetAllStoresHTML() throws Exception {
        response = priceRequest.getAllStoresHTML();
        assertTrue(response.getStatus() > 0);
    }


}
