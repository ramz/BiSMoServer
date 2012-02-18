package org.bismoapp.models;

import java.io.Serializable;
import javax.persistence.Id;

import org.json.JSONObject;

public class Tv implements Serializable {

	@Id public String tvId;
	
	//TODO: implement
	public JSONObject toJSON(){
		return null;
	}
}
