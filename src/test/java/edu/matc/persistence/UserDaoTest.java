package edu.matc.persistence;

import edu.matc.entity.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by student on 3/24/17.
 */
public class UserDaoTest {
    UserDao userDao;
    @Before
    public void setUp() {
        userDao = new UserDao();
    }

    @Test
    @Ignore
    public void testGetUserByApiKey() throws Exception {
        //int userId = dao.getUserByApiKey("system");
        //assertEquals("Should match ", 1, userId);
    }


    @Test
    public void getUser() throws Exception {
        //TODO test the getUser Methods
        User user=userDao.getUser(1);
        assertTrue(user.getUserId()==1);
    }

}