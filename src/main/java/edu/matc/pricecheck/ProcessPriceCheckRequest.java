package edu.matc.pricecheck;

import edu.matc.entity.PriceFact;
import edu.matc.persistence.BrandDao;
import edu.matc.persistence.ItemDao;
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
    private List<ItemsItem> responseItem;
    private List<PriceFact> priceFacts;
    private List<Integer> storeIds;
    private List<Integer> brandIds;
    private List<Integer> itemIds;
    PriceFactDao daoFact;
    ItemDao daoItem;
    BrandDao daoBrand;
    StoreDao daoStore;

    private final Logger log = Logger.getLogger(this.getClass());

    public ProcessPriceCheckRequest() {
        response = new Request();
        responseEntry = new ArrayList<EntryItem>();
        responseItem = new ArrayList<ItemsItem>();
        priceFacts = new ArrayList<PriceFact>();
        daoFact = new PriceFactDao();
        daoItem = new ItemDao();
        daoBrand = new BrandDao();
        daoStore = new StoreDao();

    }

    public ProcessPriceCheckRequest(Request request) {
        this();
        this.request = request;
    }

    public Request processResponse() {

        procesGrocery(request.getUserLatitude(), request.getUserLongtitude(), (double) request.getRadiusMile());
        processEntry();

        response.setAction("response");
        response.setApikey(request.getApikey());
        response.setEntry(responseEntry);
        response.setOutput(request.getOutput());
        response.setRadiusMile(request.getRadiusMile());
        response.setType(request.getType());
        response.setUserLatitude(request.getUserLatitude());
        response.setUserLongtitude(request.getUserLongtitude());

        return response;

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
            daoFact.getItemPrice(item.getName(), item.getUnit(), item.getUnitValue(), item.getBrand(), storeIds);
        } catch (Exception e) {
            log.error("Error getting prices from price fact ", e);
        }
    }
}
