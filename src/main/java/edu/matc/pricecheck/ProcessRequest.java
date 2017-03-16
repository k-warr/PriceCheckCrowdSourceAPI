package edu.matc.pricecheck;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

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

    }
}
