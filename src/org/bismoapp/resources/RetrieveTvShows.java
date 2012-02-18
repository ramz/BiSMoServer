package org.bismoapp.resources;

import org.bismoapp.models.Show;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class RetrieveTvShows extends ServerResource{

 @Get("json")
  public Representation acceptRepresentation(Representation entity){
	 String tvId = (String) getRequest().getAttributes().get("tvId");
	 //TODO: check that the TVID exists
	 
	 Objectify ofy = ObjectifyService.begin();

	 Query<Show> q = ofy.query(Show.class).filter("tvId =", tvId);


     try {
    	JSONArray showItems = new JSONArray();
	   	 for (Show show: q) {
		     showItems.put(show.toJSON());
		 }
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("shows", showItems);
    	jsonObj.put("tvId", tvId);
      	JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
   	 	return jsonRep;
     } catch (Exception e) {
         e.printStackTrace();
     }
return null;
}
}