package org.bismoapp;

import org.bismoapp.resources.Start;
import org.bismoapp.resources.Ping;
import org.bismoapp.resources.Pong;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class BismoApplication extends Application{

	 /**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createRoot() {
	    // Create a router Restlet that routes each call to a
	    // new Resource
	    Router router = new Router(getContext());
	    router.attachDefault(Start.class);
	    router.attach("/ping", Ping.class);
	    router.attach("/pong", Pong.class);
	    return router;
	}
}