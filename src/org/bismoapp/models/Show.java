package org.bismoapp.models;

import java.io.Serializable;

import javax.persistence.Id;

import org.json.JSONException;
import org.json.JSONObject;

public class Show implements Serializable {

	@Id public Long id;
	private String tvId;
	private String showName;
	private String showParameter;
	private String appId;
	private int showDuration;
	private int totalVotes=0;
	private int numRounds=0;
	
	public String getTvId() {
		return tvId;
	}
	public void setTvId(String tvId) {
		this.tvId = tvId;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}	
	public String getShowParameter() {
		return showParameter;
	}
	public void setShowParameter(String showParameter) {
		this.showParameter = showParameter;
	}		
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public int getShowDuration() {
		return showDuration;
	}
	public void setShowDuration(int showDuration) {
		this.showDuration = showDuration;
	}

	public int getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}

	public int getNumRounds() {
		return numRounds;
	}
	public void setNumRounds(int numRounds) {
		this.numRounds = numRounds;
	}	
	//TODO: implement
	public JSONObject toJSON() throws JSONException{
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", showName);
		jsonObj.put("parameter", showParameter);		
		jsonObj.put("showId", id);
		jsonObj.put("appId", appId);
		jsonObj.put("showDuration", showDuration);
		jsonObj.put("totalVotes", totalVotes);
		return jsonObj;
	}
}
