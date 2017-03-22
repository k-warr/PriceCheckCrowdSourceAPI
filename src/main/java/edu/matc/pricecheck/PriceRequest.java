package edu.matc.pricecheck;

/**
 * Created by student on 3/4/17.
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
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
    @Path("/JSON/update/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsgPlainJSON(@PathParam("param") String msg,
                                    @QueryParam("input") String input) {


        ObjectMapper mapper = new ObjectMapper();
        Request request = null;
        try {
            request = mapper.readValue(input, Request.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        request.getRadiusMile();

        return Response.status(200).entity(input).build();
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

