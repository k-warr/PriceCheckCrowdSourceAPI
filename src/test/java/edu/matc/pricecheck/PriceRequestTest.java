
package edu.matc.pricecheck;

import com.sun.deploy.config.ClientConfig;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


import java.util.HashMap;
import java.util.Map;

import static edu.matc.pricecheck.PriceRequest.getNearestGroceryStores;
import static org.junit.Assert.*;


/**
 * Created by student on 3/23/17.
 */

public class PriceRequestTest {

    WebTarget target;
    String pathVars;

    @Before
    public void setup() {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/pricerequest");
        pathVars = "";

    }

    @Test
    public void getHelloTest() {
        System.out.println(target.request().accept(MediaType.TEXT_PLAIN).get(String.class));
    }

    @Test
    public void getMsgPlainJSONTest() {
//        System.out.println(target.request().accept(MediaType.APPLICATION_JSON).get(String.class));
        pathVars = "JSON/request?name=Peanut Butter&brand=Skippy&lon=-89.213428000&lat=43.183093000&distance=10";
        String output = target.path(pathVars).request().accept(MediaType.APPLICATION_JSON).get(String.class);
//        String expected = "<html> <title>Hello Jersey</title><body><h1>Hello param=param&key=value in HTML</h1></body></html>";
        System.out.println(output);
//        assertEquals(expected, output);
    }

    @Test
    public void getNearestGroceryStoresTest() throws Exception {
        Map<String, Map<String, String>> results = getNearestGroceryStores("43.105825", "-89.336998", "1");

        System.out.println(results);
    }


}
