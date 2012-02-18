package org.bismoapp.models;

import java.io.Serializable;
import javax.persistence.Id;

import org.json.JSONObject;

public class Voting implements Serializable {

	@Id Long id;
	private String clientId;
	private Long showId;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public Long getShowId() {
		return showId;
	}
	public void setShowId(Long showId) {
		this.showId = showId;
	}
	
	//TODO: implement
	public JSONObject toJSON(){
		return null;
	}
}
