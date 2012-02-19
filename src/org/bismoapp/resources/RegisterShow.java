 package org.bismoapp.resources;

import org.bismoapp.models.Show;
import org.bismoapp.models.Tv;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class RegisterShow extends ServerResource{

 @SuppressWarnings("deprecation")
@Post
  public Representation acceptRepresentation(Representation entity){
	 Objectify ofy = ObjectifyService.begin();
	 
	 Form form = getRequest().getEntityAsForm();
	 String tvId = form.getFirstValue("tvId");
	 String appId =  form.getFirstValue("appId");
	 int showDuration =  Integer.valueOf(form.getFirstValue("showDuration")).intValue();
	 String showName =  form.getFirstValue("showName");
	 String showParameter =  form.getFirstValue("showParameter");

	 //TODO: check that the tvId is valid
	 //TODO: check that the appId is valid
	 //TODO: check that all params are available and valid
	 
	 //check if a show with the same name,tvid and parameter exists, no need to add it if it exists
	 boolean found = false;
	 Query<Show> showQ = ofy.query(Show.class).filter("showParameter = ", showParameter);
	 for(Show s: showQ){
		 if((s.getTvId().equals(tvId)) && (s.getShowName().equals(showName))){
			 found = true;
		 }
	 }
	 if(!found){
	     Show show = new Show();
	     show.setTvId(tvId);
	     show.setAppId(appId);
	     show.setShowDuration(showDuration);
	     show.setShowName(showName);
	     show.setShowParameter(showParameter);
	     show.setTotalVotes(0);
	     ofy.put(show);
	 }


     try {
    	 Query<Show> q = ofy.query(Show.class).filter("tvId =", tvId);
    	JSONArray showItems = new JSONArray();
	   	 for (Show s2: q) {
		     showItems.put(s2.toJSON());
		 }
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("shows", showItems);
    	if(found){
    		jsonObj.put("message", "show is already listed for this tv. Not added");	
    	}
      	JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
   	 	return jsonRep;
     } catch (Exception e) {
         e.printStackTrace();
     }
return null;
}
}