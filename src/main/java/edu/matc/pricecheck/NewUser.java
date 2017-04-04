package edu.matc.pricecheck;

import edu.matc.entity.User;
import edu.matc.persistence.UserDao;
import org.apache.log4j.Logger;

/**
 * Created by student on 3/28/17.
 */
public class NewUser {

    private final Logger log = Logger.getLogger(this.getClass());

    public String getApiKey(String format) {
        User user = new User();
        UserDao dao = new UserDao();
        String message = "Error in adding new user.";
        user.generateApiKey();

        try {
            dao.addUser(user);
            message = user.getApiKey();
        } catch (Exception e) {
            log.error("Error adding new user", e);
        }

        if (format.equals("J")) {
            message =  "{\"apiKey\" : \""+ message + "\"}";
        } else  {
            message = "<h2><span>apiKey:</span>" + message + "</h2>";
        }

        return message;
    }

}
