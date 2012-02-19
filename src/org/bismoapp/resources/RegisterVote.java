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

	public static int votesPerUser = 5;
 @Post
  public Representation acceptRepresentation(Representation entity){
	 String showString = (String) getRequest().getAttributes().get("showId");
	 Long showId = Long.valueOf(showString);
	 String clientId = (String) getRequest().getAttributes().get("clientId");
     Objectify ofy = ObjectifyService.begin();
     
     //update show table totalVotes
     Show showFetched = ofy.get(Show.class,showId);
     
	 //TODO: check that client is still allowed to vote > return error message otherwise
	 int numClientVotesThisRound=0;
     Query<Voting> qV = ofy.query(Voting.class).filter("clientId =", clientId);
     for(Voting v: qV){
    	 numClientVotesThisRound++;
     }
	 if(numClientVotesThisRound == votesPerUser){
	     try {
	    	 Query<Show> q = ofy.query(Show.class).filter("tvId =", showFetched.getTvId());
        	JSONArray showItems = new JSONArray();
    	   	 for (Show show: q) {
    		     showItems.put(show.toJSON());
    		 }
        	JSONObject jsonObj = new JSONObject();
        	jsonObj.put("shows", showItems);
	     	jsonObj.put("message", "You have exceeded your vote limit for this round (5 votes)");
	       	JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
	    	 	return jsonRep;
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	 }
	 
	 //save vote
     Voting voting = new Voting();
     voting.setClientId(clientId);
     voting.setShowId(showId);

     ofy.put(voting);
     
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