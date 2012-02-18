package org.bismoapp.models;

import java.io.Serializable;
import javax.persistence.Id;

public class Tv implements Serializable {

	@Id Long id;
	private String tvId;

	public String getTvId() {
		return tvId;
	}

	public void setTvId(String tvId) {
		this.tvId = tvId;
	}
	
}
