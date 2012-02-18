package org.bismoapp.resources;

import org.bismoapp.models.Client;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class RegisterClient extends ServerResource{

 @Post("json")
  public Representation acceptRepresentation(Representation entity){
     Objectify ofy = ObjectifyService.begin();
     //delete all previous entries with the client ID
     
     Client client = new Client();
     client.setClientId((String) getRequest().getAttributes().get("clientId"));
     client.setTvId((String) getRequest().getAttributes().get("tvId"));
     
     ofy.put(client);

     try {
    	Client clientFetched = ofy.query(Client.class).filter("clientId", client.getClientId()).get();
    	JSONObject jsonObj = new JSONObject();
      	jsonObj.put("message", "Client registered");
      	jsonObj.put("tvId",clientFetched.getTvId());
      	jsonObj.put("clientId",clientFetched.getClientId());
      	JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
   	 	return jsonRep;
     } catch (Exception e) {
         e.printStackTrace();
     }
     return null;
 }
}