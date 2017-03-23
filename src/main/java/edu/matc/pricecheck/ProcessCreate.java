package edu.matc.pricecheck;

import edu.matc.persistence.UserDao;

/**
 * Created by student on 3/22/17.
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
        boolean validUser = checkKeyApi();

        return "";
    }

    private boolean checkKeyApi() {
        UserDao dao = new UserDao();

        dao.getUserByName(apiKey);
        return false;
    }
}
