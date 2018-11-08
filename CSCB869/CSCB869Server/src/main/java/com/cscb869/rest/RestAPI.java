package com.cscb869.rest;

import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.cscb869.HelperMethods;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Miglen Evlogiev
 */
@Path("/")
public class RestAPI {

    /**
     * This methods redirects to the rest-api.html static page in the main path.
     */
    @GET
    @Path("/")
    public Response getApi() {
        // redirect to the location of the API Docs
        return Response.seeOther(URI.create("../rest-api.html")).build();
    }    
    
    /**
     * This method consumes the post json requests and produces output from the
     * text manipulation using the helper methods.
     * @param input - json in text with text and needle keys with string values.
     * @return String json - output as response from the API
     */
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postApi(String input) {

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(input);
            JSONObject json_input = (JSONObject) obj;
            JSONObject output = new JSONObject();
            
            
            String text = (String) json_input.get("text");
            String needle = (String) json_input.get("needle");
            
            // 
            output.put("findSubstr", String.valueOf(HelperMethods.findSubstr(text, needle)));
            output.put("getNumberOfSymbols", String.valueOf(HelperMethods.getNumberOfSymbols(text)));
            output.put("invertString", String.valueOf(HelperMethods.invertString(text)));
            output.put("getNumberOfWords", String.valueOf(HelperMethods.getNumberOfWords(text)));

            return Response.status(200).entity(output.toJSONString()).build();
        } catch (Exception ex) {
            Logger.getLogger(RestAPI.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(500).build();
        }
    }
}
