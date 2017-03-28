package edu.matc.pricecheck;

import edu.matc.entity.*;
import edu.matc.persistence.*;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by student on 3/22/17.
 *
 */
public class ProcessCreate {
    private final Logger log = Logger.getLogger(this.getClass());
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
    User userObject;
    PriceFact priceFact;

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
        message = "Added Successfully!";

        if (validKeyApi() && validInput()) {
            addPriceFact();
        }

        return "{\"message\" : \""+ message + "\"}";
    }

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

/*
            priceFact.setItemByItemId(itemObject);
            priceFact.setUserByUserId(userObject);
            priceFact.setStoreByStoreId(storeObject);
            priceFact.setBrandByBrandId(brandObject);
*/
            priceFact.setUserId(userObject.getUserId());
            priceFact.setItemId(itemObject.getItemId());
            priceFact.setStoreId(storeObject.getStoreId());
            priceFact.setBrandId(brandObject.getBrandId());
            priceFact.setPriceAmount(BigDecimal.valueOf(itemPrice));
            priceFact.setFactDateTime(timestamp);
            dao.addPriceFact(priceFact);
        }
    }

    private void addStore() {
        StoreDao dao = new StoreDao();
        List<Store> stores = dao.getExactStore(storeName, storeAddress,
                latitude, longtitude);
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

    private boolean validInput() {
        boolean validItem = (item != null) && (item.matches("^[^\\x00-\\x1F\\x80-\\x9F]+$"));

        if (!validItem) {
            message = "Item is not valid";
            return false;
        }
        return true;
    }

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
