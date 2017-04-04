package edu.matc.pricecheck;

import edu.matc.entity.*;
import edu.matc.entity.Store;

import edu.matc.persistence.*;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by student on 3/22/17.
 * This class will add new items into the database and its supporting
 * information.
 */
public class ProcessCreate {
    private final Logger log = Logger.getLogger(this.getClass());
    private String item;
    private double itemPrice;
    private String itemUnit;
    private int itemUnitValue;
    private String brandName;
    private String storeName;
    private String storeAddress;
    private double latitude;
    private double longtitude;
    private String apiKey;
    private String format;
    private String message;
    private Item itemObject;
    private Store storeObject;
    private Brand brandObject;
    private User userObject;
    private PriceFact priceFact;

    /**
     * This is the empty constructor that initializes base objects.
     */
    public ProcessCreate() {
        itemObject = new Item();
        storeObject = new Store();
        brandObject = new Brand();
        priceFact = new PriceFact();
    }

    /**
     * This constructor accepts all possible attributes to create the items
     * @param item - This is the item that will be added
     * @param itemPrice - This is the price of the item
     * @param itemUnit - This is the quantity unit of the item.
     * @param itemUnitValue - This is the quantity amount in units of the item.
     * @param brandName - This is the brand name of the item.
     * @param storeName - This is the name of the store this item is found.
     * @param storeAddress - This is the address of the store.
     * @param latitude - This is the latitude of the store address.
     * @param longtitude - This is the longtitude of the store address.
     * @param apiKey - This is the user key adding the item.
     * @param format - This determines if the out put is HTML or JSON.
     */
    public ProcessCreate(String item, double itemPrice, String itemUnit,
                         int itemUnitValue, String brandName, String storeName,
                         String storeAddress, double latitude, double longtitude,
                         String apiKey, String format) {
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
        this.format = format;

    }

    /**
     * This makes string valid
     * @param string
     * @return
     */
    private String checkString(String string) {
        boolean isNull = (string == null);
        boolean isEmpty = false;
        boolean isSpace = false;

        if (!isNull) {
            isEmpty = string.equals("");
            isSpace = string.equals(" ");
        }

        if (isNull || isEmpty || isSpace) {
            return "<none>";
        } else {
            return string;
        }
    }

    /**
     * This is the method that validates, adds and returns the result of the
     * validation and process.
     * @return This is the message of the the process
     */
    public String getMessage() {
        message = "Added Successfully!";

        if (validKeyApi() && validInput()) {
            addPriceFact();

        }

        if (format.equals("J")) {
            message =  "{\"message\" : \""+ message + "\"}";
        } else  {
            message = "<h2><span>message:</span>" + message + "</h2>";
        }

        return message;
    }

    /**
     * This adds the item price into the database.
     */
    private void addPriceFact() {
        PriceFactDao dao = new PriceFactDao();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());;


        if (itemPrice <= 0.10) {
            message = "Item is too cheap add";
        } else if (itemPrice >= 500.00){
            message = "Item is too expensive to add";
        } else {

            addItem();
            addStore();
            addBrand();

            priceFact.setUserId(userObject.getUserId());
            priceFact.setItemId(itemObject.getItemId());
            priceFact.setStoreId(storeObject.getStoreId());
            priceFact.setBrandId(brandObject.getBrandId());
            priceFact.setPriceAmount(BigDecimal.valueOf(itemPrice));
            priceFact.setFactDateTime(timestamp);

            dao.addPriceFact(priceFact);
        }
    }

    /**
     * This adds a new store in the database if it does not exists.
     */
    private void addStore() {
        StoreDao dao = new StoreDao();
        List<Store> stores = null;

        if (storeName.equals("<none>")) {
            stores = dao.getExactStore(storeName, 0.0, 0.0);
        } else {
            stores = dao.getExactStore(storeName, latitude, longtitude);
        }

        if (stores.size() == 0) {
            storeObject.setLatitude(BigDecimal.valueOf(latitude));
            storeObject.setLongtitude(BigDecimal.valueOf(longtitude));
            storeObject.setStoreName(storeName);
            storeObject.setStoreAddress(storeAddress);

            storeObject.setStoreId(dao.addStore(storeObject));
        } else {
            storeObject = stores.get(0);
        }

        if (storeObject == null) {
            message = "No store entered.";
        }

    }

    /**
     * This adds a new brand in the database if it does not exists.
     */
    private void addBrand() {
        BrandDao dao = new BrandDao();
        List<Brand> brands = dao.getExactBrand(brandName);

        if (brands.size() == 0) {
            brandObject.setBrandName(brandName);
            brandObject.setBrandId(dao.addBrand(brandObject));
        } else {
            brandObject = brands.get(0);
        }

        if (brandObject == null) {
            brandObject.setBrandId(1);
            brandObject.setBrandName("unknown");
        }
    }

    /**
     * This adds the new item in the database if it does not exists.
     */
    private void addItem() {
        ItemDao dao = new ItemDao();
        List<Item> items = dao.getExactItem(item, itemUnit, itemUnitValue);
        if (items.size() == 0) {
            itemObject.setItemName(item);
            itemObject.setUnit(itemUnit);
            itemObject.setUnitValue(itemUnitValue);
            itemObject.setItemId(dao.addItem(itemObject));
        } else {
            itemObject = items.get(0);
        }

        if (itemObject == null) {
            message = "No Item entered.";
        }

    }

    /**
     * Validates item string to include no invalid character and not null.
     * @return This returns true if the item string is valid.
     */
    private boolean validInput() {

        boolean validItem = (item != null) && (item.matches("^[^\\x00-\\x1F\\x80-\\x9F]+$"));

        if (!validItem) {
            message = "Item is not valid";

            return false;
        }
        return true;
    }

    /**
     * Validates user API key that it exists in the database to add new
     * information.
     * @return This returns true if the user is authorized to add new items.
     */
    private boolean validKeyApi() {
        UserDao dao = new UserDao();

        try {
            userObject = dao.getUserByApiKey(apiKey);
            if (userObject == null) {
                message = "User is not authorized to add items.";
            }
        } catch (Exception e) {
            message = "Error checking apiKey";
            log.error("Error checking apiKey ", e);
        }

        return (userObject != null);

    }
}

