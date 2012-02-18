 package org.bismoapp.resources;

import org.bismoapp.models.Show;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class RegisterShow extends ServerResource{

 @SuppressWarnings("deprecation")
@Post
  public Representation acceptRepresentation(Representation entity){
	 //TODO: check that the tvId is valid
	 //TODO: check that the appId is valid
	 //TODO: check that all params are available
	 //TODO: check that the show doesn't exist (compare on appId & showName)

	 Form form = getRequest().getEntityAsForm();
	 String tvId = form.getFirstValue("tvId");
	 String appId =  form.getFirstValue("appId");
	 int showDuration =  Integer.valueOf(form.getFirstValue("showDuration")).intValue();
	 String showName =  form.getFirstValue("showName");
	 String showParameter =  form.getFirstValue("showParameter");
	 
     Show show = new Show();
     show.setTvId(tvId);
     show.setAppId(appId);
     show.setShowDuration(showDuration);
     show.setShowName(showName);
     show.setShowParameter(showParameter);
     show.setTotalVotes(0);
     Objectify ofy = ObjectifyService.begin();
     ofy.put(show);
     
     try {
    	//TODO: return proper response (show object?)
    	JSONObject jsonObj = new JSONObject();
      	jsonObj.put("message", "Show created");
      	jsonObj.put("showId",show.id);
      	JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
   	 	return jsonRep;
     } catch (Exception e) {
         e.printStackTrace();
     }
return null;
}
}