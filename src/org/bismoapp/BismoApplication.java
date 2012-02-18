package org.bismoapp;

import org.bismoapp.models.Client;
import org.bismoapp.models.NextShow;
import org.bismoapp.models.Show;
import org.bismoapp.models.Tv;
import org.bismoapp.models.TvApp;
import org.bismoapp.models.Voting;
import org.bismoapp.resources.CloseVoting;
import org.bismoapp.resources.RegisterClient;
import org.bismoapp.resources.RegisterShow;
import org.bismoapp.resources.RegisterTv;
import org.bismoapp.resources.RegisterTvApp;
import org.bismoapp.resources.RegisterVote;
import org.bismoapp.resources.RetrieveNextShow;
import org.bismoapp.resources.RetrieveTvApps;
import org.bismoapp.resources.RetrieveTvShows;
import org.bismoapp.resources.Start;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.googlecode.objectify.ObjectifyService;

public class BismoApplication extends Application{

	 /**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createRoot() {
		//Register Datastore kinds
	    ObjectifyService.register(Client.class);
	    ObjectifyService.register(Tv.class);
	    ObjectifyService.register(Voting.class);
	    ObjectifyService.register(Show.class);
	    ObjectifyService.register(NextShow.class);
	    ObjectifyService.register(TvApp.class);
	    
	    Router router = new Router(getContext());
	    router.attachDefault(Start.class);
	    
	    //register client/tv combo
	    router.attach("/tv/{tvId}/client/{clientId}",RegisterClient.class);
	    
	    //register tv
	    router.attach("/tv/{tvId}",RegisterTv.class);
	    
	    //get shows available to a tv
	    router.attach("/tv/{tvId}/shows",RetrieveTvShows.class);

	    //get apps available to a tv
	    router.attach("/tv/{tvId}/apps",RetrieveTvApps.class);
	    
	    //create a show
	    router.attach("/show",RegisterShow.class);
	    
	    //get the next show coming up on a tv
	    router.attach("/tv/{tvId}/nextShow",RetrieveNextShow.class);

	    //vote for a show
	    router.attach("/show/{showId}/client/{clientId}",RegisterVote.class);
	    
	    //close voting on a show
	    router.attach("/tv/{tvId}/closeVoting",CloseVoting.class);
	    
	    //add apps to a tv
	    router.attach("/tv/{tvId}/addApp",RegisterTvApp.class);
	    
	    return router;
	}
}