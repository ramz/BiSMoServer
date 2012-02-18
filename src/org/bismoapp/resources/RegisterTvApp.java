 package org.bismoapp.resources;

import org.bismoapp.models.TvApp;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class RegisterTvApp extends ServerResource{

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
	 String appName =  form.getFirstValue("appName");
	 String appParameter =  form.getFirstValue("appParameter");
	 
     TvApp tvApp = new TvApp();
     tvApp.setTvId(tvId);
     tvApp.setAppId(appId);
     tvApp.setAppName(appName);
     tvApp.setAppParameter(appParameter);
     Objectify ofy = ObjectifyService.begin();
     ofy.put(tvApp);
     
     try {
    	//TODO: return proper response (show object?)
    	JSONObject jsonObj = new JSONObject();
      	jsonObj.put("message", "App created");
      	jsonObj.put("appId",tvApp.getAppId());
      	JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
   	 	return jsonRep;
     } catch (Exception e) {
         e.printStackTrace();
     }
     return null;
 }
}