package org.bismoapp.models;

import java.io.Serializable;

import javax.persistence.Id;

public class NextShow implements Serializable {

	@Id Long id;
	private String tvId;
	private String showId;
	private String showName;
	private String appId;
	private int showDuration;
	private int totalVotes;
	
	public String getTvId() {
		return tvId;
	}
	public void setTvId(String tvId) {
		this.tvId = tvId;
	}
	public String getShowId() {
		return showId;
	}
	public void setShowId(String showId) {
		this.showId = showId;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
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
	
}
