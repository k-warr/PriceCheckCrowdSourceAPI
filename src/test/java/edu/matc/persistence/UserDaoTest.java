package edu.matc.persistence;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Punitha Anandan on 3/11/2017.
 */
public class UserDaoTest {
    UserDao userDao;

    @Before
    public void setup() {
        userDao = new UserDao();
    }

    @Test
    public void getUser() throws Exception {
        UserEntity userEntity = userDao.getUserEntity(1);
        assertTrue(userEntity.getApiKey().equalsIgnoreCase("1234444"));
    }
}
