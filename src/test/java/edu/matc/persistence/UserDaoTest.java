package edu.matc.persistence;

import edu.matc.entity.User;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by student on 3/24/17.
 */
public class UserDaoTest {
    UserDao dao;

    @Before
    public void setUp() {
        dao = new UserDao();
    }

    @Test
    public void getUser() throws Exception {
        //TODO test the getUser Methods
        User user = dao.getUser(1);
        assertTrue(user.getUserId() == 1);
        UserDao dao;
    }


    @Test
    public void getUserByApiKey() throws Exception {
        User user = dao.getUserByApiKey("system");
        assertEquals("Should match ", 1, user.getUserId());

    }

}