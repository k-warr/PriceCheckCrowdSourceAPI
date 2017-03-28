package edu.matc.pricecheck;

import com.sun.deploy.config.ClientConfig;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

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
        System.out.println(target.request().accept(MediaType.TEXT_PLAIN).get(String.class));
//        String output = target.path("param=param&key=value/HTML").request().accept(MediaType.TEXT_HTML).get(String.class);
//        String expected = "<html> <title>Hello Jersey</title><body><h1>Hello param=param&key=value in HTML</h1></body></html>";
//        System.out.println(output);
//        assertEquals(expected, output);
    }


}