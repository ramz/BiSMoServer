package org.bismoapp.resources;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;



public class Ping extends ServerResource{

 @Get("json")
  public Representation represent(){
  // Generate the right representation according to its media type.
          try {
           JSONObject jsonObj = new JSONObject();
           jsonObj.put("answer", "pong");
           JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
        	  return jsonRep;        	  
              // Generate a DOM document representing the list of
              // items.
//        	  DomRepresentation domRep = new DomRepresentation(MediaType.TEXT_XML);
//              Document d = domRep.getDocument();
//              Element r = d.createElement("expire_date");
//              d.appendChild(r);
//               Element date = d.createElement("date");
//               date.appendChild(d.createTextNode(new Date().toLocaleString()));
//               r.appendChild(date);
//              d.normalizeDocument();
//  
//              // Returns the XML representation of this document.
//              return domRep;
           
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