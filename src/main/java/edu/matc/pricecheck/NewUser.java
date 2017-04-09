package edu.matc.pricecheck;

import edu.matc.entity.User;
import edu.matc.persistence.UserDao;
import org.apache.log4j.Logger;

/**
 * Created by student on 3/28/17.
 */
public class NewUser {

    private final Logger log = Logger.getLogger(this.getClass());

    public void getApiKey(String format) {
        User user = new User();
        UserDao dao = new UserDao();
        String message = null;

        try {
            user.generateApiKey();
            dao.addUser(user);
            message = user.getApiKey();
        } catch (Exception e) {
            log.error("Error adding new user" + e.getMessage(), e);
            RunMessage.setMessage("Error adding new user! " + e.getMessage());
            RunMessage.setStatus(500);
        }

        if (format.equals("J") && RunMessage.getStatus() == 200) {
            RunMessage.setMessage("{\"apiKey\" : \""+ message + "\"}");
        } else if (RunMessage.getStatus() == 200)  {
            RunMessage.setMessage("<h2><span>apiKey:</span>" + message +
                    "</h2>");
        }

    }

}
