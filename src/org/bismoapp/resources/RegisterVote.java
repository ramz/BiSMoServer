package org.bismoapp.resources;

import org.bismoapp.models.Show;
import org.bismoapp.models.Voting;
import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class RegisterVote extends ServerResource{

	public static int votesPerUser = 3;
 @Post
  public Representation acceptRepresentation(Representation entity){
	 String showString = (String) getRequest().getAttributes().get("showId");
	 Long showId = Long.valueOf(showString);
	 String clientId = (String) getRequest().getAttributes().get("clientId");
     
	 //TODO: check that client is still allowed to vote > return error message otherwise
	 //TODO: check that the show actually exists > return error message otherwise
	 //save vote
     Voting voting = new Voting();
     voting.setClientId(clientId);
     voting.setShowId(showId);
     Objectify ofy = ObjectifyService.begin();
     ofy.put(voting);
     
     //update show table totalVotes
     Show showFetched = ofy.get(Show.class,showId);
     if(showFetched!=null){
    	 showFetched.setTotalVotes(showFetched.getTotalVotes()+1);
    	 ofy.put(showFetched);
     }

	 Query<Show> q = ofy.query(Show.class).filter("tvId =", showFetched.getTvId());


     try {
    	JSONArray showItems = new JSONArray();
	   	 for (Show show: q) {
		     showItems.put(show.toJSON());
		 }
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("shows", showItems);
      	JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
   	 	return jsonRep;
     } catch (Exception e) {
         e.printStackTrace();
     }
return null;
}
}