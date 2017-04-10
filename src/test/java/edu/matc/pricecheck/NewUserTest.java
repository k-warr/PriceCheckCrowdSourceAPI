package edu.matc.pricecheck;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


/**
 * Created by student on 4/5/17.
 */
public class NewUserTest {
    @Test
    public void getApiKey() throws Exception {
        NewUser user = new NewUser();
        user.getApiKey("J");
        assertTrue ("Key is JSON",RunMessage.getMessage().contains("}"));

        user.getApiKey("H");
        assertTrue ("Key is JSON",RunMessage.getMessage().contains(">"));

    }

}