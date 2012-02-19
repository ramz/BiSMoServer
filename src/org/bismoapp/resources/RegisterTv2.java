package org.bismoapp.resources;

import org.bismoapp.models.Tv;
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

public class RegisterTv2 extends ServerResource{

	 @Get("html")
	  public Representation represent(){	 
		 StringBuilder stringBuilder = new StringBuilder();
	     stringBuilder.append("<html>");
	     stringBuilder.append("<head><title>Welcome to BiSMo</title></head>");
	     stringBuilder.append("<body><h1>Welcome to BiSMo</h1>");
	     stringBuilder.append("<a href='https://market.android.com/details?id=org.bismo.client'>Download the app here!</a>");
	     stringBuilder.append("</html>");
	     return new StringRepresentation(stringBuilder.toString(),MediaType.TEXT_HTML);
	 }
 @Post("json")
  public Representation acceptRepresentation(Representation entity){
     Objectify ofy = ObjectifyService.begin();
     String tvId = (String) getRequest().getAttributes().get("tvId"); 
     //Checking and deleting an older instance of the tv
     Tv tvFetched = ofy.query(Tv.class).filter("tvId", tvId).get();
     if(tvFetched != null){
    	 ofy.delete(tvFetched);
     }
     Tv tv = new Tv();
     tv.tvId = tvId;
     ofy.put(tv);
     try {
    	JSONObject jsonObj = new JSONObject();
      	jsonObj.put("message", "Tv registered");
      	jsonObj.put("tvId",tv.tvId);
      	JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
   	 	return jsonRep;
     } catch (Exception e) {
         e.printStackTrace();
     }
return null;
}
}