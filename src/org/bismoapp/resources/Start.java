package org.bismoapp.resources;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;



public class Start extends ServerResource{

 @Get("json")
  public Representation represent(){
  // Generate the right representation according to its media type.
          try {
           JSONObject jsonObj = new JSONObject();
           jsonObj.put("message", "welcome to BiSMo");
           JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
        	  return jsonRep;
          } catch (Exception e) {
              e.printStackTrace();
          }
  return null;
 }
 
 @Post
  public Representation acceptRepresentation(Representation entity){
  //Could process some post requests here and possibly write to the datastore
  return null;
 }
}