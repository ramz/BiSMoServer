package org.bismoapp.resources;

import org.bismoapp.models.TvApp;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class RetrieveTvApps extends ServerResource{

 @Get("json")
  public Representation acceptRepresentation(Representation entity){
	 String tvId = (String) getRequest().getAttributes().get("tvId");
	 //TODO: check that the TVID exists
	 
	 Objectify ofy = ObjectifyService.begin();

	 Query<TvApp> q = ofy.query(TvApp.class).filter("tvId =", tvId);


     try {
    	JSONArray appItems = new JSONArray();
	   	 for (TvApp tvApp: q) {
	   		appItems.put(tvApp.toJSON());
		 }
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("apps", appItems);
      	JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
   	 	return jsonRep;
     } catch (Exception e) {
         e.printStackTrace();
     }
return null;
}
}