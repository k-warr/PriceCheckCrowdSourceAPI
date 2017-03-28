package edu.matc.pricecheck;

import edu.matc.entity.User;
import edu.matc.persistence.UserDao;

/**
 * Created by student on 3/28/17.
 */
public class NewUser {

    public String getApiKey() {
        User user = new User();
        UserDao dao = new UserDao();


        return user.getApiKey();
    }

}
