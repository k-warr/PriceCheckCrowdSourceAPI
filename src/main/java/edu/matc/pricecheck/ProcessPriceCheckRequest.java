package edu.matc.pricecheck;

import edu.matc.entity.PriceFact;
import edu.matc.persistence.PriceFactDao;
import edu.matc.persistence.StoreDao;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 3/4/17.
 */
public class ProcessPriceCheckRequest {
    //TODO Roderick
  /*  jsoup Chelsea
            String getItem()
               String postItem()
                 String   generateAPIKey()
*/
    private Request request;
    private Request response;
    private List<EntryItem> responseEntry;
    private List<PriceFact> priceFacts;
    private List<Integer> storeIds;
    private PriceFactDao daoFact;
    private StoreDao daoStore;

    private final Logger log = Logger.getLogger(this.getClass());

    public ProcessPriceCheckRequest() {
        response = new Request();
        responseEntry = new ArrayList<EntryItem>();
        priceFacts = new ArrayList<PriceFact>();
        daoFact = new PriceFactDao();
        daoStore = new StoreDao();

    }

    public ProcessPriceCheckRequest(Request request) {
        this();
        this.request = request;
    }

    public Request processResponse() {

        procesGrocery(request.getUserLatitude(), request.getUserLongtitude(), (double) request.getRadiusMile());
        processEntry();

        if (priceFacts !=null) {
            response.setAction("response");
            response.setApikey(request.getApikey());
            response.setEntry(responseEntry);
            response.setOutput(request.getOutput());
            response.setRadiusMile(request.getRadiusMile());
            response.setType(request.getType());
            response.setUserLatitude(request.getUserLatitude());
            response.setUserLongtitude(request.getUserLongtitude());

            completeResponse();

            return response;
        } else {
            return null;
        }
    }

    private void completeResponse() {
        int storeId = 0;
        int itemId = 0;
        int brandId = 0;
        boolean firstItem = false;
        boolean newItem = false;
        boolean newStore = false;
        boolean newBrand = false;

        EntryItem entryItem = null;
        ItemsItem itemsItem = null;
        List<EntryItem> entryList = new ArrayList<EntryItem>();
        List<ItemsItem> itemList = new ArrayList<ItemsItem>();

        for (PriceFact priceFact: priceFacts) {

            firstItem = (storeId == 0 && itemId == 0 && brandId == 0);
            newStore = (storeId != priceFact.getStoreId());
            newItem = (itemId != priceFact.getItemId());
            newBrand = (brandId != priceFact.getBrandId());

            if (firstItem) {
                storeId = priceFact.getStoreId();
                itemId = priceFact.getItemId();
                brandId = priceFact.getBrandId();

                entryItem = addNewStore(priceFact);
                itemList.add(addNewItem(priceFact));

            } else if (newStore) {
                storeId = priceFact.getStoreId();

                entryItem.setItems(itemList);
                entryList.add(entryItem);

                entryItem = addNewStore(priceFact);
                itemList.add(addNewItem(priceFact));

            } else if (newItem || newBrand) {
                itemId = priceFact.getItemId();
                brandId = priceFact.getBrandId();
                itemList.add(addNewItem(priceFact));
            }

        }

        entryItem.setItems(itemList);
        entryList.add(entryItem);

        response.setEntry(entryList);
    }

    private ItemsItem addNewItem(PriceFact priceFact) {

        ItemsItem itemsItem = new ItemsItem();
        itemsItem.setName(priceFact.getItemByItemId().getItemName());
        itemsItem.setPriceDollar(priceFact.getPriceAmount());
        itemsItem.setUnit(priceFact.getItemByItemId().getUnit());
        itemsItem.setUnitValue(priceFact.getItemByItemId().getUnitValue());

        return itemsItem;
    }

    private EntryItem addNewStore(PriceFact priceFact) {

        Grocery grocery = new Grocery();
        grocery.setLatitude(priceFact.getStoreByStoreId().getLatitude());
        grocery.setLongtitude(priceFact.getStoreByStoreId().getLongtitude());
        grocery.setName(priceFact.getStoreByStoreId().getStoreName());

        EntryItem entryItem = new EntryItem();
        entryItem.setGrocery(grocery);

        return entryItem;
    }


    private void procesGrocery(double userLatitude, double userLongtitude, double radiusMile) {

        try {

            storeIds = daoStore.getNearestStoreId(userLatitude,
                    userLongtitude, radiusMile);

        } catch (Exception e) {
            log.error("Error getting grocery store ", e);
        }
    }

    private void processEntry() {

        for (EntryItem entry: request.getEntry()) {

            processItems(entry);
        }

    }

    private void processItems(EntryItem entryItem) {
        for (ItemsItem item: entryItem.getItems()) {
            processItem(item);
        }
    }

    private void processItem(ItemsItem item) {

        try {
            priceFacts.addAll(daoFact.getItemPrice(item.getName(), item.getUnit(),
                    item.getUnitValue(), item.getBrand(), storeIds));
        } catch (Exception e) {
            log.error("Error getting prices from price fact ", e);
        }
    }
}
