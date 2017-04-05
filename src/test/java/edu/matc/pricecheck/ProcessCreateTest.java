package edu.matc.pricecheck;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by student on 4/1/17.
 */
public class ProcessCreateTest {
    @Ignore
    @Test
    public void getMessage1() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system", "J");

        assertEquals("Good Message is not returned", "{\"message\" : \"Added Successfully!\"}", processCreate
                .getMessage());

    }
    @Test
    public void getMessage2() throws Exception {
        ProcessCreate processCreate = new ProcessCreate(null,99.99,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system", "J");

        assertEquals("Item is not valid Message is not returned", "{\"message\" : \"Item is not valid\"}", processCreate
                .getMessage());

    }
    @Test
    public void getMessage3() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>", 0.0,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system", "J");

        assertEquals("Good Message is not returned", "{\"message\" : \"Item is too cheap add\"}", processCreate
                .getMessage());

    }
    @Test
    public void getMessage4() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>", 501.0,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system", "J");

        assertEquals("Good Message is not returned", "{\"message\" : \"Item is too expensive to add\"}", processCreate
                .getMessage());

    }
    @Ignore
    @Test
    public void getMessage5() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                null, 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system", "J");

        assertEquals("Good Message is not returned", "{\"message\" : \"Added Successfully!\"}", processCreate
                .getMessage());

    }
    @Ignore
    @Test
    public void getMessage6() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 11, "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system", "J");

        assertEquals("Good Message is not returned", "{\"message\" : \"Added Successfully!\"}", processCreate
                .getMessage());

    }
    @Ignore
    @Test
    public void getMessage7() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 99, " ",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system", "J");

        assertEquals("Good Message is not returned", "{\"message\" : \"Added Successfully!\"}", processCreate
                .getMessage());

    }
    @Ignore
    @Test
    public void getMessage8() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 99, "<testBrandName",
                null, "<testStoreAddress>", 0.000000, 0.0000,
                "system", "J");

        assertEquals("Good Message is not returned", "{\"message\" : \"Added Successfully!\"}", processCreate
                .getMessage());
    }
    @Ignore
    @Test
    public void getMessage9() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "", 0.000000, 0.0000,
                "system", "J");

        assertEquals("Good Message is not returned", "{\"message\" : \"Added Successfully!\"}", processCreate
                .getMessage());

    }
    @Ignore
    @Test
    public void getMessage10() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress2nd>", 1.000000,
                1.0000,
                "system", "J");

        assertEquals("Good Message is not returned", "{\"message\" : \"Added Successfully!\"}", processCreate
                .getMessage());

    }
    @Test
    public void getMessage11() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress2nd>", 1.000000,
                1.0000,
                "", "J");

        assertEquals("Bad Message is not returned", "{\"message\" : \"Error checking apiKey\"}", processCreate
                .getMessage());

    }
    @Ignore
    @Test
    public void getMessage12() throws Exception {
        ProcessCreate processCreate = new ProcessCreate("<testItem>",99.99,
                "<testItemUnit>", 99, "<testBrandName",
                "<testStoreName>", "<testStoreAddress>", 0.000000, 0.0000,
                "system", "H");

        assertEquals("Good Message is not returned", "<h2><span>message:</span>Added Successfully!</h2>", processCreate
                .getMessage());

    }

}