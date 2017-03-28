package edu.matc.pricecheck;

/**
 * Created by student on 3/4/17.
 */

import edu.matc.persistence.GeoLocation;
import edu.matc.persistence.ItemDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 3/1/17.
 */
@Path("/pricerequest")
public class PriceRequest {
    //TODO Pass in Request
    // The Java method will process HTTP GET requests

    @POST
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/JSON/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsgPlainJSON(@QueryParam("item") String item,
                                    @QueryParam("itemPrice") double itemPrice,
                                    @QueryParam("itemUnit") String itemUnit,
                                    @QueryParam("itemUnitValue") String itemUnitValue,
                                    @QueryParam("brandName") String brandName,
                                    @QueryParam("storeName") String storeName,
                                    @QueryParam("storeAddress") String storeAddress,
                                    @QueryParam("latitude") double latitude,
                                    @QueryParam("longtitude") double longtitude,
                                    @QueryParam("apiKey") String apiKey) {

        ProcessCreate processCreate = new ProcessCreate(item,itemPrice,
                itemUnit,Integer.valueOf(itemUnitValue),brandName,storeName,
                storeAddress,
                latitude, longtitude, apiKey);

        String output = processCreate.getMessage();

        return Response.status(200).entity(output).build();
    }

    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/JSON/request")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsgPlainJSON(@QueryParam("name") String itemName,
                                    @QueryParam("brand") String brandName,
                                    @QueryParam("lon") double longtitude,
                                    @QueryParam("lat") double latitude,
                                    @QueryParam("distance") double distance
    ) {
        Request request = null;

        request = processMessage(itemName, brandName, longtitude,
                latitude, distance);

        // Return a simple message
//        ProcessRequest processRequest = new ProcessRequest();
//        String output = processRequest.getItem(request);

        String stringOfParams = "name=" + itemName + ", brand=" + brandName;

        return Response.status(200).entity(stringOfParams).build();
    }

    /**
     * Quick test to see if API is up and running
     *
     * @return String "Hello"
     */
    @GET
    @Path("")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getHello() {
        String output = "Hello";
        return Response.status(200).entity(output).build();
    }

    private Request processMessage(String itemName, String
            brandName, double longtitude, double latitude, double distance) {
        String parameter = null;

        return createRequest(itemName, brandName, latitude, longtitude,
                distance);
    }

    private Request createRequest(String itemName, String brandName, double
            latitude, double longtitude, double distance) {
        Request request = new Request();
        List<EntryItem> entryList = new ArrayList<EntryItem>();
        List<ItemsItem> itemList = new ArrayList<ItemsItem>();
        EntryItem entryItem = new EntryItem();
        ItemsItem itemsItem = new ItemsItem();

        GeoLocation userLocation = GeoLocation.fromDegrees(latitude, longtitude);


        // get grocery stores within distance of long lat
        // get items WHERE name, brand, etc. AND groceryId IN (previous search)
        // format results into json
        // add to request



//        if (itemName != null && brandName == null && ) {
//
//        }

        request.setAction("request");
        request.setRadiusMile((int) distance);
        request.setUserLatitude(latitude);
        request.setUserLongtitude(longtitude);
        itemsItem.setName(itemName);
        itemsItem.setBrand(brandName);
        itemList.add(itemsItem);
        entryItem.setItems(itemList);
        entryList.add(entryItem);
        request.setEntry(entryList);

//        ItemDao

        return request;
    }

}

