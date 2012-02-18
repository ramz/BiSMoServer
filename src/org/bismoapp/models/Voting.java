package org.bismoapp.models;

import java.io.Serializable;
import javax.persistence.Id;

public class Voting implements Serializable {

	@Id Long id;
	private String clientId;
	private String showId;
	private String tvId;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getShowId() {
		return showId;
	}
	public void setShowId(String showId) {
		this.showId = showId;
	}
	public String getTvId() {
		return tvId;
	}
	public void setTvId(String tvId) {
		this.tvId = tvId;
	}
}
