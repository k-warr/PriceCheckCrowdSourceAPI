package edu.matc.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by student on 3/28/17.
 */
public class UserTest {
    @Test
    public void generateApiKey() throws Exception {
        String apiKey = new User().getApiKey();
        assertEquals("no equal", " ", apiKey);
    }

}