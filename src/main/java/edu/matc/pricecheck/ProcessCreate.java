package edu.matc.pricecheck;

import edu.matc.persistence.UserDao;
import org.apache.log4j.Logger;

/**
 * Created by student on 3/22/17.
 * @since 1.6
 */
public class ProcessCreate {
    String item;
    double itemPrice;
    String itemUnit;
    String itemUnitValue;
    String brandName;
    String storeName;
    String storeAddress;
    double latitude;
    double longtitude;
    String apiKey;
    String message;
    private final Logger log = Logger.getLogger(this.getClass());

    public ProcessCreate() {}

    public ProcessCreate(String item, double itemPrice, String itemUnit,
                         String itemUnitValue, String brandName, String storeName,
                         String storeAddress, double latitude, double longtitude,
                         String apiKey) {
        this();
        this.apiKey = apiKey;
        this.brandName = brandName;
        this.item = item;
        this.itemPrice = itemPrice;
        this.itemUnit = itemUnit;
        this.itemUnitValue = itemUnitValue;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.storeName = storeName;
        this.storeAddress = storeAddress;

    }

    public String getMessage() {
        if (validKeyApi() && validInput()) {
            addPriceFact();
        }

        return message;
    }

    private void addPriceFact() {

    }

    private boolean validInput() {
        boolean validItem = (item != null) && (item.equals(" ")) && (item
                .equals("")) && (item.matches("[ -~]"));

        if (!validItem) {
            message = "{\"message\": \"Item is not valid\"}";
            return false;
        }
        return true;
    }

    private boolean validKeyApi() {
        UserDao dao = new UserDao();
        int userId = 0;

        try {
            userId = dao.getUserByApiKey(apiKey);
        } catch (Exception e) {
            log.error("Error checking apiKey ", e);
        }

        return (userId > 0);
    }
}
