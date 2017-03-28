package edu.matc.pricecheck;

/**
 * Created by student on 3/4/17.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.maps.GoogleMapsApiResponse;
import edu.matc.persistence.GeoLocation;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
@Path("/pricerequest")
public class PriceRequest {
    private final Logger log = Logger.getLogger(this.getClass());
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
//        GoogleMapsApiResponse response = mapper.readValue(url)

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


}

