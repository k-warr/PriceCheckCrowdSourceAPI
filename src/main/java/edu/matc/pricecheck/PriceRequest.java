package edu.matc.pricecheck;

/**
 * Created by student on 3/4/17.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.matc.entity.Brand;
import edu.matc.entity.Item;
import edu.matc.entity.Store;
import edu.matc.persistence.BrandDao;
import edu.matc.persistence.ItemDao;
import edu.matc.persistence.StoreDao;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Path("/pricerequest")
public class PriceRequest {
    private final Logger log = Logger.getLogger(this.getClass());
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

    @GET
    @Path("/JSON/items")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() throws IOException {
        // Return all items
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ItemDao itemDao = new ItemDao();
        List userList = (ArrayList<Item>)itemDao.getAllItems();
        String arrayToJson = null;

        try {
            arrayToJson = mapper.writeValueAsString(userList);
        } catch (JsonProcessingException jsonProcessingException) {
            log.info("JsonProcessingException",jsonProcessingException);
        }
        return Response.status(200).entity(arrayToJson).build();
    }


    @GET
    @Path("/JSON/brands")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBrand() throws IOException {
        // Return all brands
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        BrandDao brandDao = new BrandDao();
        List userList = (ArrayList<Brand>)brandDao.getAllBrand();
        String arrayToJson = null;

        try {
            arrayToJson = mapper.writeValueAsString(userList);
        } catch (JsonProcessingException jsonProcessingException) {
            log.info("JsonProcessingException",jsonProcessingException);
        }
        return Response.status(200).entity(arrayToJson).build();
    }

    @GET
    @Path("/JSON/stores")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStores() throws IOException {
        // Return all stores
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        StoreDao storeDao = new StoreDao();
        List userList = (ArrayList<Store>)storeDao.getAllStores();
        String arrayToJson = null;
        try {
            arrayToJson = mapper.writeValueAsString(userList);
        } catch (JsonProcessingException jsonProcessingException) {
            log.info("JsonProcessingException",jsonProcessingException);
        }
        return Response.status(200).entity(arrayToJson).build();
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