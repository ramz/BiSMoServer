package org.bismoapp.resources;

import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;



public class Start2 extends ServerResource{

 @Get
  public Representation represent(){	 
	 StringBuilder stringBuilder = new StringBuilder();
     stringBuilder.append("<html>");
     stringBuilder.append("<head><title>Welcome to BiSMo</title></head>");
     stringBuilder.append("<body><h1>Welcome to BiSMo</h1>");
     stringBuilder.append("<a href='https://market.android.com/details?id=org.bismo.client'>Download the app here!</a>");
     stringBuilder.append("</html>");
     return new StringRepresentation(stringBuilder.toString(),MediaType.TEXT_HTML);
 }
 @Post
  public Representation acceptRepresentation(Representation entity){
  //Could process some post requests here and possibly write to the datastore
  return null;
 }
}