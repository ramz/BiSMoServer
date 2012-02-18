package org.bismoapp.models;

import java.io.Serializable;
import javax.persistence.Id;

public class TvApp implements Serializable {

	@Id Long id;
	private String tvId;
	private String appId;
	private String appName;
	private String requiredParam;
	
	public String getTvId() {
		return tvId;
	}
	public void setTvId(String tvId) {
		this.tvId = tvId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getRequiredParam() {
		return requiredParam;
	}
	public void setRequiredParam(String requiredParam) {
		this.requiredParam = requiredParam;
	}
	
}
