package org.bismoapp.resources;

import org.bismoapp.models.Client;
import org.bismoapp.models.Show;
import org.bismoapp.models.Tv;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class RetrieveNextShow extends ServerResource{

 @Get("json")
  public Representation acceptRepresentation(Representation entity){
	 String tvId = (String) getRequest().getAttributes().get("tvId");
	 //get all shows available for the tv;
//	 Objectify ofy = ObjectifyService.begin();
//
//	 Query<Show> q = ofy.query(Show.class).filter("tvId >", tvId);
//	 for (Show show: q) {
//	     //build JSon...
//	 }

     try {
    	JSONObject jsonObj = new JSONObject().put("name", "Tobi's mama").put("showId", 12342353).put("appShowId", "1aq235eF").put("appId", "youporn").put("showDuration", 500).put("totalVotes", 4);
      	JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
   	 	return jsonRep;
     } catch (Exception e) {
         e.printStackTrace();
     }
return null;
}
}