package edu.matc.pricecheck;

/**
 * Created by student on 3/4/17.
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by student on 3/1/17.
 */


@Path("/pricerequest")
public class PriceRequest {
    //TODO Pass in Request
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/{param}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMsgPlain(@PathParam("param") String msg) {
        // Return a simple message
        String output = "Hello " + msg + " in plain";
        return Response.status(200).entity(output).build();
    }
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/{param}/HTML")
    @Produces(MediaType.TEXT_HTML)
    public Response getMsgHTML(@PathParam("param") String msg) {
        // Return a simple message
        String output = "<html> " + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello " + msg + " in HTML" + "</h1></body>"
                + "</html> ";
        return Response.status(200).entity(output).build();
    }
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/{param}/JSON")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsgPlainJSON(@PathParam("param") String msg) {
        // Return a simple message
        String output = "{msg:Hello " + msg + " in JSON}";
        return Response.status(200).entity(output).build();
    }
}

