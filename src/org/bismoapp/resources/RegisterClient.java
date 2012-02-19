package org.bismoapp.resources;

import org.bismoapp.models.Client;
import org.bismoapp.models.Tv;
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
     String clientId = (String) getRequest().getAttributes().get("clientId");
     String tvId = (String) getRequest().getAttributes().get("tvId");
     
     //removing the client if it's been previously registered.
     Client clientFetched = ofy.query(Client.class).filter("clientId", clientId).get();
     if(clientFetched != null){
    	 ofy.delete(clientFetched);
     }
     Client client = new Client();
     client.setClientId(clientId);
     client.setTvId(tvId);
     
     ofy.put(client);

     try {
    	JSONObject jsonObj = new JSONObject();
      	jsonObj.put("message", "Client registered");
      	jsonObj.put("tvId",client.getTvId());
      	jsonObj.put("clientId",client.getClientId());
      	JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
   	 	return jsonRep;
     } catch (Exception e) {
         e.printStackTrace();
     }
     return null;
 }
}