
package edu.matc.pricecheck;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entity.Brand;
import edu.matc.entity.Item;
import edu.matc.entity.Store;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;



/**
 * Created by student on 3/23/17.
 */

public class PriceRequestTest {

    Client client;
    WebTarget target;
    String pathVars;

    PriceRequest priceRequest;
    Response response;


    @Before
    public void setup() {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/pricerequest");
        pathVars = "";
        priceRequest = new PriceRequest();
    }
    @Test
    public void addItemJSON() throws Exception {
        response = priceRequest.addItemJSON("<testItem>",99.99,
                "<testItemUnit>", "99", "<testBrandName",
               "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system");
        assertEquals("Return code should be 500", 500, response
                .getStatus());
        assertEquals("Message expected is not met", "{\"message\" : \"Added Successfully!\"}" , response.getEntity().toString());
    }

    @Test
    public void addItemHTML() throws Exception {
        response = priceRequest.addItemHTML("<testItem>",99.99,
                "<testItemUnit>", "99", "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system");
        assertEquals("Return code should be 500", 500, response
                .getStatus());
        assertEquals("Message expected is not met", "<h2><span>message:</span>Added Successfully!</h2>" , response.getEntity().toString());
    }

    @Test
    public void addNewUserJSON() throws Exception {
        response = priceRequest.addNewUserJSON();
        assertEquals("Return code should be 300", 300, response
                .getStatus());
        assertEquals("Message expected is not met", "{\"apiKey\" : ",
                response.getEntity().toString().substring(0,12));
    }

    @Test
    public void addNewUserHTML() throws Exception {
        response = priceRequest.addNewUserHTML();
        assertEquals("Return code should be 300", 300, response
                .getStatus());
        assertEquals("Message expected is not met",
                "<h2><span>apiKey:</span>",
                response.getEntity().toString().substring(0,24));
    }

    @Before
    public void set() {
        client =  ClientBuilder.newClient();;
    }

    @Test
    public void testGetAllItems() throws Exception {
        WebTarget target =
                client.target("http://localhost:8080/pricerequest/JSON/items" );
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Item> items = Arrays.asList( mapper.readValue(response.toString(),Item[].class));
        assertTrue(items.size()>0);


    }

    @Test
    public void testGetAllBrand() throws Exception {
        WebTarget target =
                client.target("http://localhost:8080/pricerequest/JSON/brands" );
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Brand> brands = Arrays.asList( mapper.readValue(response.toString(),Brand[].class));
        assertTrue(brands.size()>0);
    }

    @Test
    public void testGetAllStores() throws Exception {
        WebTarget target =
                client.target("http://localhost:8080/pricerequest/JSON/stores" );
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Store> stores = Arrays.asList( mapper.readValue(response.toString(),Store[].class));
        assertTrue(stores.size()>0);
    }






//    @Test
//    public void getHelloTest() {
//        System.out.println(target.request().accept(MediaType.TEXT_PLAIN).get(String.class));
//    }
//
//    @Test
//    public void getMsgPlainJSONTest() {
//        System.out.println(target.request().accept(MediaType.TEXT_PLAIN).get(String.class));
////        String output = target.path("param=param&key=value/HTML").request().accept(MediaType.TEXT_HTML).get(String.class);
////        String expected = "<html> <title>Hello Jersey</title><body><h1>Hello param=param&key=value in HTML</h1></body></html>";
////        System.out.println(output);
////        assertEquals(expected, output);
//    }
//
//    @Test
//    public void getNearestGroceryStoresTest() throws Exception {
//        Map<String, Map<String, String>> results = new HashMap<String, Map<String, String>>();
//
//        results = getNearestGroceryStores("43.105825", "-89.336998", "1");
//
//        System.out.println(results);
//    }


}
