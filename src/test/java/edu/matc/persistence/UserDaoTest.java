package edu.matc.persistence;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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
    @Ignore
    public void getUserByApiKey() throws Exception {
        //int userId = dao.getUserByApiKey("system");
        //assertEquals("Should match ", 1, userId);

    }

}