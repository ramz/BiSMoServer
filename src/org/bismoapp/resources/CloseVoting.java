package org.bismoapp.resources;

import java.util.Random;

import org.bismoapp.models.NextShow;
import org.bismoapp.models.Show;
import org.bismoapp.models.Voting;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class CloseVoting extends ServerResource{

	public static int votesPerUser = 3;
 @Post
  public Representation acceptRepresentation(Representation entity){
	 String tvId = (String) getRequest().getAttributes().get("tvId");
	 
	 Objectify ofy = ObjectifyService.begin();

	 //get next show with tvId
	 NextShow currentNextShow = ofy.query(NextShow.class).filter("tvId =", tvId).get();
	 if(currentNextShow !=null){
		 ofy.delete(currentNextShow);
	 }
	 //find show with most votes
	 Query<Show> showVotes = ofy.query(Show.class).filter("tvId =", tvId);
	 Show mostVoted = new Show();
	 mostVoted.setTotalVotes(-1);
	 int numShows = 0;
   	 for (Show show: showVotes) {
   		 numShows++;
   		 if(show.getTotalVotes()>mostVoted.getTotalVotes()){
   			 mostVoted = show;
   		 }
	 }

   	 if(mostVoted.getTotalVotes()==0){
   	   	 Random randomGenerator = new Random();
   	   	 int randomInt = randomGenerator.nextInt(numShows);
   	   	 int counter = 0;
   	   	 for (Show show: showVotes) {
   	   		 if(counter == randomInt){
   	   			 mostVoted = show;
   	   		 }
   	   		 counter++;
   		 }
   	 }
   	 
   	 currentNextShow = new NextShow();
   	 //update next show with content from show with most votes
   	 currentNextShow.setTvId(mostVoted.getTvId());
	 currentNextShow.setShowName(mostVoted.getShowName());
	 currentNextShow.setShowParameter(mostVoted.getShowParameter());
	 currentNextShow.setShowDuration(mostVoted.getShowDuration());
	 currentNextShow.setTotalVotes(mostVoted.getTotalVotes());
	 currentNextShow.setAppId(mostVoted.getAppId());
	 ofy.put(currentNextShow);
	 
	 //reset show with most votes to 0 total votes
	 mostVoted.setTotalVotes(0);
	 ofy.put(mostVoted);
	 
	 //remove all clientIds for the showId from voting
	 Query<Voting> votesFetched = ofy.query(Voting.class).filter("showId =", mostVoted.id);
     try {
	   	 for (Voting voting: votesFetched) {
		     ofy.delete(voting);
		 }
     } catch (Exception e) {
         e.printStackTrace();
     }
     
     try {
     	JSONObject jsonObj = currentNextShow.toJSON();
       	JsonRepresentation jsonRep = new JsonRepresentation(jsonObj);
    	return jsonRep;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return null;
 }
}