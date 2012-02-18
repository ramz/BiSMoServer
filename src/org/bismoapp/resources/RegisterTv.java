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

public class RegisterTv extends ServerResource{

 @Get("html")
  public Representation represent(){	 
	 StringBuilder stringBuilder = new StringBuilder();
     stringBuilder.append("<html>");
     stringBuilder.append("<head><title>BiSMo</title></head>");
     stringBuilder.append("<body style='background-color:#0f0'><h1><blink>Patience... landing pages coming soon<blink></h1><body>");
     stringBuilder.append("</html>");
     return new StringRepresentation(stringBuilder.toString(),MediaType.TEXT_HTML);
 }
 @Post("json")
  public Representation acceptRepresentation(Representation entity){
     Objectify ofy = ObjectifyService.begin();
     Tv tv = new Tv();
     tv.setTvId((String) getRequest().getAttributes().get("tvId"));
     ofy.put(tv);
     try {
    	Tv tvFetched = ofy.query(Tv.class).filter("tvId", tv.getTvId()).get();
    	JSONObject jsonObj = new JSONObject();
      	jsonObj.put("message", "Tv registered");
      	jsonObj.put("tvId",tvFetched.getTvId());
      	JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
   	 	return jsonRep;
     } catch (Exception e) {
         e.printStackTrace();
     }
return null;
}
}