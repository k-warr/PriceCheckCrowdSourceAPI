package edu.matc.pricecheck;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by student on 4/1/17.
 */
public class ProcessCreateTest {

    @Test
    public void getMessage1() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system");
        processCreate.execute();
        assertEquals("Good Message is not returned", "{\"message\" : " +
                "\"200 : Added Successfully!\"}", RunMessage.getMessageJSON());

    }
    @Test
    public void getMessage2() throws Exception {
        ProcessCreate processCreate = new ProcessCreate(null,99.99,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system");
        processCreate.execute();
        assertEquals("Item is not valid Message is not returned",
                "{\"message\" : \"400 : Item is not valid\"}", RunMessage
                        .getMessageJSON());

    }
    @Test
    public void getMessage3() throws Exception{
        ProcessCreate processCreate = new ProcessCreate("<testItem>", 0.0,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system");
        processCreate.execute();
        assertEquals("Item is not valid Message is not returned",
                "{\"message\" : \"400 : Item is too cheap add\"}", RunMessage
                        .getMessageJSON());

    }

    @Test
    public void getMessage4() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>", 501.0,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system");

        processCreate.execute();
        assertEquals("Good Message is not returned", "{\"message\" : \"400 : Item is too expensive to add\"}",RunMessage.getMessageJSON());

    }

    @Test
    public void getMessage5() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                null, 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system");
        processCreate.execute();
        assertEquals("Good Message is not returned", "{\"message\" : \"200 : Added Successfully!\"}", RunMessage.getMessageJSON());

    }

    @Test
    public void getMessage7() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 99, " ",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system");
        processCreate.execute();
        assertEquals("Good Message is not returned", "{\"message\" : \"200 " +
                ": Added Successfully!\"}", RunMessage.getMessageJSON());

    }

    @Test
    public void getMessage8() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 99, "<testBrandName>",
                null, "<testStoreAddress>", 0.000000, 0.0000,
                "system");
        processCreate.execute();
        assertEquals("Good Message is not returned", "{\"message\" : \"200 " +
                ": Added Successfully!\"}", RunMessage.getMessageJSON());
    }

    @Test
    public void getMessage9() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "", 0.000000, 0.0000,
                "system");
        processCreate.execute();
        assertEquals("Good Message is not returned", "{\"message\" : \"200 " +
                ": Added Successfully!\"}", RunMessage.getMessageJSON());

    }

    @Test
    public void getMessage10() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress2nd>", 1.000000,
                1.0000,
                "system");
        processCreate.execute();
        assertEquals("Good Message is not returned", "{\"message\" : \"200 " +
                ": Added Successfully!\"}", RunMessage.getMessageJSON());

    }
    @Test
    public void getMessage11() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress2nd>", 1.000000,
                1.0000,
                "");
        processCreate.execute();
        assertEquals("Bad Message is not returned", "{\"message\" : \"500 : Error checking apiKey\"}", RunMessage.getMessageJSON());

    }
  
    @Test
    public void getMessage12() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system");
        processCreate.execute();
        assertEquals("Good Message is not returned",
                "<h2><span>message:</span>200 : Added Successfully!</h2>",
                RunMessage.getMessageHTML());

    }

}