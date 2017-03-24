package edu.matc.pricecheck;

/**
 * Created by student on 3/4/17.
 */

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
                                    @QueryParam("distance") double distance) {
        ProcessRequest processRequest = null;
        Request request = null;

        request = processMessage(itemName, brandName, longtitude,
                latitude, distance);

        // Return a simple message
        processRequest = new ProcessRequest();
        String output = processRequest.getItem(request);
        return Response.status(200).entity(output).build();
    }

    @GET
    // Test path to see if program is working
    @Path("")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMsgPlainJSON() {
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

        return request;
    }
}

