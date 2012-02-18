package org.bismoapp.models;

import java.io.Serializable;

import javax.persistence.Id;

import org.json.JSONObject;

public class Client implements Serializable{

	@Id public Long id;
	private String clientId;
	private String tvId;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getTvId() {
		return tvId;
	}
	public void setTvId(String tvId) {
		this.tvId = tvId;
	}
	
	//TODO: implement
	public JSONObject toJSON(){
		return null;
	}
}