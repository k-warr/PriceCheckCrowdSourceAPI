package edu.matc.pricecheck;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private EntryItem responseEntry;
    private ItemsItem responseItem;
    private List<Integer> storeIds;
    private List<Integer> itemIds;
    private List<Integer> brandIds;

    private final Logger log = Logger.getLogger(this.getClass());

    public ProcessRequest() {

    }

    public ProcessRequest(Request request) {
        this();
        this.request = request;
    }

    public String getItem(Request request) {

        if (this.request == null) {
            this.request = request;
        }

        response = new ProcessPriceCheckRequest(request).processResponse();

        ObjectMapper mapper = new ObjectMapper();
        String output = null;


        try {
            output = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.error("error mapping response", e);
        }

        return output;
    }

}