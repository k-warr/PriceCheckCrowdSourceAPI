package edu.matc.pricecheck;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.persistence.StoreDao;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by student on 3/4/17.
 */
public class ProcessRequest {
    //TODO Roderick
  /*  jsoup Chelsea
            String getItem()
               String postItem()
                 String   generateAPIKey()
*/
    private Request request;
    private Request response;
    private final Logger log = Logger.getLogger(this.getClass());

    public ProcessRequest() {
        response = new Request();
    }

    public ProcessRequest(Request request) {
        this();
        this.request = request;
    }

    public String getItem(Request request) {
        this.request = request;
        processEntry();
        ObjectMapper mapper = new ObjectMapper();
        String output = null;


        try {
            output = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.error("error mapping response", e);
        }

        return output;
    }

    private void processEntry() {

        for (EntryItem entry: request.getEntry()) {
            processItems(entry);
            procesGrocery(request.getUserLatitude(), request.getUserLongtitude(), (double) request.getRadiusMile());
        }

    }

    private void procesGrocery(double userLatitude, double userLongtitude, double radiusMile) {
        List<Integer> grocerId = new StoreDao().getNearestStore(userLatitude,
                userLongtitude, radiusMile);
    }


    private void processItems(EntryItem entryItem) {
        for (ItemsItem item: entryItem.getItems()) {
            processItem(item);
        }
    }

    private void processItem(ItemsItem item) {
        ItemsItem newItem = item;
        item.getName();
        item.getBrand();
        item.getUnit();
        item.getUnitValue();
    }
}
