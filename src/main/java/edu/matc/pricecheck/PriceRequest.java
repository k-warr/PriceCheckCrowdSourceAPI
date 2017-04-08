package edu.matc.pricecheck;

/**
 * Created by student on 3/4/17.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.maps.GoogleMapsApiResponse;
import com.google.maps.ResultsItem;
import edu.matc.entity.PriceFact;
import edu.matc.persistence.*;
import edu.matc.entity.Brand;
import edu.matc.entity.Item;
import edu.matc.entity.Store;

import edu.matc.persistence.BrandDao;
import edu.matc.persistence.GeoLocation;
import edu.matc.persistence.ItemDao;
import edu.matc.persistence.StoreDao;

import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by student on 3/1/17.
 */
@Path("/")
public class PriceRequest {
    private final Logger log = Logger.getLogger(this.getClass());
    // The Java method will process HTTP GET requests
    // The Java method will produce content identified by the MIME Media type "text/plain"
    /**
     * The Java method will produce content identified by the MIME Media type
     * "JSON" This adds a new entry to the price check database for a user.
     * @param item - this is the grocery item that will be priced
     * @param itemPrice - this is the price of the item reported.
     * @param itemUnit - this is the unit of the item entered.
     * @param itemUnitValue - this is the quantity of the unit entered.
     * @param brandName - this is the brand of the item
     * @param storeName - this item as priced is found in this store.
     * @param storeAddress - this is the store address
     * @param latitude - this is the latitude of the store address
     * @param longtitude - this is the longtitude of the store address
     * @param apiKey - this is the user key who reported the price
     * @return - this returns the status and message.
     */

    @POST
    @Path("/JSON/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addItemJSON(@FormParam("item") String item,
                                    @FormParam("itemPrice") double itemPrice,
                                    @FormParam("itemUnit") String itemUnit,
                                    @FormParam("itemUnitValue") String itemUnitValue,
                                    @FormParam("brandName") String brandName,
                                    @FormParam("storeName") String storeName,
                                    @FormParam("storeAddress") String storeAddress,
                                    @FormParam("latitude") double latitude,
                                    @FormParam("longtitude") double longtitude,
                                    @FormParam("apiKey") String apiKey) {


        ProcessCreate processCreate = null;
        String output = null;

        try {
            processCreate = new ProcessCreate(item,itemPrice,
                    itemUnit,Integer.valueOf(itemUnitValue),brandName,storeName,
                    storeAddress, latitude, longtitude, apiKey, "J");
            output = processCreate.getMessage();

        } catch (Exception e) {
            return Response.status(Integer.valueOf(e.getCause().getMessage())).entity
                    (e.getMessage()).build();
        }


        return Response.status(500).entity(output).build();
    }

    /**
     * The Java method will produce content identified by the MIME Media type
     * "HTML" This adds a new entry to the price check database for a user.
     * @param item - this is the grocery item that will be priced
     * @param itemPrice - this is the price of the item reported.
     * @param itemUnit - this is the unit of the item entered.
     * @param itemUnitValue - this is the quantity of the unit entered.
     * @param brandName - this is the brand of the item
     * @param storeName - this item as priced is found in this store.
     * @param storeAddress - this is the store address
     * @param latitude - this is the latitude of the store address
     * @param longtitude - this is the longtitude of the store address
     * @param apiKey - this is the user key who reported the price
     * @return - this returns the status and message.
     */
    @POST
    @Path("/HTML/create")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addItemHTML(@FormParam("item") String item,
                                    @FormParam("itemPrice") double itemPrice,
                                    @FormParam("itemUnit") String itemUnit,
                                    @FormParam("itemUnitValue") String itemUnitValue,
                                    @FormParam("brandName") String brandName,
                                    @FormParam("storeName") String storeName,
                                    @FormParam("storeAddress") String storeAddress,
                                    @FormParam("latitude") double latitude,
                                    @FormParam("longtitude") double longtitude,
                                    @FormParam("apiKey") String apiKey) {

        ProcessCreate processCreate = null;
        String output = null;
        try {
            processCreate = new ProcessCreate(item,itemPrice,
                    itemUnit,Integer.valueOf(itemUnitValue),brandName,storeName,
                    storeAddress, latitude, longtitude, apiKey, "H");
            output = processCreate.getMessage();
        } catch (Exception e) {
            return Response.status(Integer.valueOf(e.getCause().getMessage())).entity
                    (e.getMessage()).build();
        }



        return Response.status(200).entity(output).build();
    }


    /**
     * Adds new user and message in JSON
     * @return - This returns the apiKey of the new user.
     */
    @POST
    @Path("/JSON/newuser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addNewUserJSON() {

        String output = new NewUser().getApiKey("J");

        return Response.status(300).entity(output).build();
    }
    /**
     * Adds new user and message in HTML
     * @return - This returns the apiKey of the new user.
     */
    @POST
    @Path("/HTML/newuser")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addNewUserHTML() {

        String output = new NewUser().getApiKey("H");

        return Response.status(300).entity(output).build();
    }

    @GET
    @Path("/JSON/request")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsgPlainJSON(@QueryParam("name") String itemName,
                                    @QueryParam("brand") String brandName,
                                    @QueryParam("lon") double longtitude,
                                    @QueryParam("lat") double latitude,
                                    @QueryParam("distance") double distance) {
        PriceFactDao priceFactDao = new PriceFactDao();
        List<PriceFact> listOfPrices;
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = null;

        try {
            listOfPrices = priceFactDao.getItemPricex(itemName, brandName, latitude, longtitude, distance);
            arrayToJson = mapper.writeValueAsString(listOfPrices);
        } catch (JsonProcessingException jsonProcessingException) {
            log.info("JsonProcessingException",jsonProcessingException);
        } catch (Exception e) {
            log.info("Exception", e);
        }

        return Response.status(200).entity(arrayToJson).build();
    }

    /**
     * Takes the params and finds any price facts that exist that meet the critera.
     *
     * @param itemName   the item name
     * @param brandName  the brand name
     * @param longtitude the longtitude
     * @param latitude   the latitude
     * @param distance   the distance
     * @return formatted html table of results
     */
    @GET
    @Path("/HTML/request")
    @Produces(MediaType.TEXT_HTML)
    public Response getMsgHTML(@QueryParam("name") String itemName,
                                    @QueryParam("brand") String brandName,
                                    @QueryParam("lon") double longtitude,
                                    @QueryParam("lat") double latitude,
                                    @QueryParam("distance") double distance) {
        PriceFactDao priceFactDao = new PriceFactDao();
        List<PriceFact> listOfPrices;
        String tableOutput = "<table><tr><th>Item Name</th><th>Brand</th><th>Price</th><th>Store Name</th><th>Address</th></tr>";

        try {
            listOfPrices = priceFactDao.getItemPricex(itemName, brandName, latitude, longtitude, distance);
            for (PriceFact priceFact : listOfPrices) {
                BrandDao brandDao = new BrandDao();
                Brand brand = brandDao.getBrand(priceFact.getBrandId());
                ItemDao itemDao = new ItemDao();
                Item item = itemDao.getItemEntity(priceFact.getItemId());
                StoreDao storeDao = new StoreDao();
                Store store = storeDao.getStore(priceFact.getStoreId());


                tableOutput += "<tr><td>" + item.getItemName() + "</td><td>"
                        + brand.getBrandName() + "</td><td>"
                        + store.getStoreName() + "</td><td>"
                        + priceFact.getPriceAmount() + "</td>"
                        + "<td>" + store.getStoreAddress() + "</td>"
                        + "</tr>"
                        + "<style>table, tr, th, td {border: 1px solid black; padding: .2em;} </style>";
            }

        } catch (Exception e) {
            log.info("Exception", e);
        }

        tableOutput += "</table>";

        return Response.status(200).type(MediaType.TEXT_HTML_TYPE).entity(tableOutput).build();
    }

    @POST
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/JSON/newuser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsgPlainJSON() {

        String output = null;

        return Response.status(200).entity(output).build();
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

//        ItemDao

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


    /**
     * Gets nearest grocery stores from user's lat/long coordinates within a given radius.
     *
     * @param lat       user's lat (in degrees)
     * @param longitude user's longitude (in degrees)
     * @param radius    radius distance (in miles)
     * @return map of the nearest grocery stores and their lat, long coords (in degrees)
     * @throws IOException the io exception
     */
    public static Map<String, Map<String, String>> getNearestGroceryStores(String lat, String longitude, String radius) throws IOException {
        Map<String, Map<String, String>> mapOfStores = new HashMap<String, Map<String, String>>();
        URL url = null;

        // Convert miles to meters
        double milesToMeters = Double.parseDouble(radius) / 0.00062137;

        String urlString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
                + lat + "," + longitude
                + "&radius=" + milesToMeters
//                + "&rankby=distance"
                + "&name=grocery"
                + "&key=AIzaSyBwHxvNrLSrxZA9GeY3ChYzPFzGbTWwMV8"; // API Key from developers.google.com DO NOT CHANGE
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Solution 1: read input line by line (NO JSON)
        /*URLConnection conn = url.openConnection();

        InputStream is = conn.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String inputLine;
        while((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();*/

        // Solution 4: Gson parser
        URLConnection conn = url.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        ObjectMapper mapper = new ObjectMapper();
        GoogleMapsApiResponse response = mapper.readValue(url, GoogleMapsApiResponse.class);
        List<ResultsItem> results = response.getResults();

        // Solution 2: JSON parser
//        conn.setDoOutput(true);
//        Scanner scanner = new Scanner(url.openStream());
//        String response = scanner.useDelimiter("\\Z").next();
//        GoogleMapsResponse json = (response);
//        scanner.close();

        // Solution 3: JSON Tokener
//        URI uri = null;
//        try {
//            uri = new URI(urlString);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }

//        JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
//        JSONObject root = new JSONObject(tokener);

        return mapOfStores;
    }



    @GET
    @Path("/JSON/items")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() throws IOException {
        // Return all items
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ItemDao itemDao = new ItemDao();
        List itemList = (ArrayList<Item>)itemDao.getAllItems();
        String arrayToJson = null;

        try {
            arrayToJson = mapper.writeValueAsString(itemList);
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


    @GET
    @Path("/HTML/stores")
    @Produces(MediaType.TEXT_HTML)
    public Response getAllStoresHTML() {
        StoreDao storeDao = new StoreDao();
        List<Store> listOfStores;
        String tableOutput = "<table><tr><th>Store Id</th><th>Store Name</th><th>longtitude</th><th>latitude</th><th>store Address</th></tr>";

        try {
            listOfStores = storeDao.getAllStores();
            for (Store store : listOfStores) {
                /*BrandDao brandDao = new BrandDao();
                Brand brand = brandDao.getBrand(priceFact.getBrandId());
                ItemDao itemDao = new ItemDao();
                Item item = itemDao.getItemEntity(priceFact.getItemId());*/
                //Store store = new Store();
                //Store store = storeDao.getAllStores();


//                String brandNameString = brand.getBrandName();
//                String itemNameString = item.getItemName();

                tableOutput += "<tr><td>" + store.getStoreId() + "</td><td>"
                        + store.getStoreName() + "</td><td>"
                        + store.getLongtitude() + "</td><td>"
                        + store.getLatitude()   + "</td><td>" + store.getStoreAddress() + "</td>"
                        + "</tr>"
                        + "<style>table, tr, th, td {border: 1px solid black; padding: .2em;} </style>";
            }

        } catch (Exception exception) {
            log.info("Exception", exception);
        }

        tableOutput += "</table>";

        return Response.status(200).type(MediaType.TEXT_HTML_TYPE).entity(tableOutput).build();
    }


    @GET
    @Path("/HTML/brands")
    @Produces(MediaType.TEXT_HTML)
    public Response getAllBrandsHTML() {
        BrandDao brandDao = new BrandDao();
        List<Brand> listOfBrands;
        String tableOutput = "<table><tr><th>Brand Id</th><th>Brand Name</th></tr>";

        try {
            listOfBrands = brandDao.getAllBrand();
            for (Brand brand : listOfBrands) {
                /*BrandDao brandDao = new BrandDao();
                Brand brand = brandDao.getBrand(priceFact.getBrandId());
                ItemDao itemDao = new ItemDao();
                Item item = itemDao.getItemEntity(priceFact.getItemId());*/
                //Store store = new Store();
                //Store store = storeDao.getAllStores();


//                String brandNameString = brand.getBrandName();
//                String itemNameString = item.getItemName();

                tableOutput += "<tr><td>" + brand.getBrandId() + "</td><td>"
                        + brand.getBrandName() + "</td>"
                        + "</tr>"
                        + "<style>table, tr, th, td {border: 1px solid black; padding: .2em;} </style>";
            }

        } catch (Exception exception) {
            log.info("Exception", exception);
        }

        tableOutput += "</table>";

        return Response.status(200).type(MediaType.TEXT_HTML_TYPE).entity(tableOutput).build();
    }


    @GET
    @Path("/HTML/items")
    @Produces(MediaType.TEXT_HTML)
    public Response getAllItemsHTML() {
        ItemDao itemDao = new ItemDao();
        List<Item> listOfItems;
        String tableOutput = "<table><tr><th>Brand Id</th><th>Brand Name</th><th>Unit</th><th>Unit value</th></tr>";

        try {
            listOfItems = itemDao.getAllItems();
            for (Item item : listOfItems) {
                /*BrandDao brandDao = new BrandDao();
                Brand brand = brandDao.getBrand(priceFact.getBrandId());
                ItemDao itemDao = new ItemDao();
                Item item = itemDao.getItemEntity(priceFact.getItemId());*/
                //Store store = new Store();
                //Store store = storeDao.getAllStores();


//                String brandNameString = brand.getBrandName();
//                String itemNameString = item.getItemName();

                tableOutput += "<tr><td>" + item.getItemId() + "</td><td>"
                        + item.getItemName() + "</td><td>"
                        + item.getUnit()+ "</td><td>"
                        + item.getUnitValue()+"</td></tr>"
                        + "<style>table, tr, th, td {border: 1px solid black; padding: .2em;} </style>";
            }

        } catch (Exception exception) {
            log.info("Exception", exception);
        }

        tableOutput += "</table>";

        return Response.status(200).type(MediaType.TEXT_HTML_TYPE).entity(tableOutput).build();
    }
}