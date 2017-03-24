package edu.matc.pricecheck;

import edu.matc.entity.Brand;
import edu.matc.entity.Item;
import edu.matc.entity.PriceFact;
import edu.matc.entity.Store;
import edu.matc.persistence.ItemDao;
import edu.matc.persistence.UserDao;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by student on 3/22/17.
 * @since 1.6
 */
public class ProcessCreate {
    String item;
    double itemPrice;
    String itemUnit;
    int itemUnitValue;
    String brandName;
    String storeName;
    String storeAddress;
    double latitude;
    double longtitude;
    String apiKey;
    String message;
    Item itemObject;
    Store storeObject;
    Brand brandObject;
    PriceFact priceFact;
    private final Logger log = Logger.getLogger(this.getClass());

    public ProcessCreate() {
        itemObject = new Item();
        storeObject = new Store();
        brandObject = new Brand();
        priceFact = new PriceFact();
    }

    public ProcessCreate(String item, double itemPrice, String itemUnit,
                         int itemUnitValue, String brandName, String storeName,
                         String storeAddress, double latitude, double longtitude,
                         String apiKey) {
        this();
        this.apiKey = apiKey;
        this.brandName = checkString(brandName);
        this.item = item;
        this.itemPrice = itemPrice;
        this.itemUnit = checkString(itemUnit);
        this.itemUnitValue = itemUnitValue;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.storeName = checkString(storeName);
        this.storeAddress = checkString(storeAddress);

    }

    private String checkString(String string) {
        boolean isNull = (string == null);
        boolean isEmpty = string.equals("");
        boolean isSpace = string.equals(" ");

        if (isNull || isEmpty || isSpace) {
            return "<none>";
        } else {
            return string;
        }
    }

    public String getMessage() {
        if (validKeyApi() && validInput()) {
            addPriceFact();
        }

        return message;
    }

    private void addPriceFact() {
        PriceFact priceFact = new PriceFact();

        addItem();
        addUser();
        addStore();
        addBrand();


    }
    private void addUser() {
    }
    private void addStore() {
    }
    private void addBrand() {
    }

    private void addItem() {
        ItemDao dao = new ItemDao();
        List<Item> items = dao.getExactItem(item, itemUnit, itemUnitValue);
        if (items.size() == 0) {
            itemObject.setItemId(dao.addItem(new Item(item, itemUnit,
                    itemUnitValue)));
        } else {
            itemObject = items.get(0);
        }

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
