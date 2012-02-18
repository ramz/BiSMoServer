package org.bismoapp.models;

import java.io.Serializable;

import javax.persistence.Id;

import org.json.JSONException;
import org.json.JSONObject;

public class TvApp implements Serializable {

	@Id Long id;
	private String tvId;
	private String appId;
	private String appName;
	private String appParameter;
	
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
	public String getAppParameter() {
		return appParameter;
	}
	public void setAppParameter(String appParameter) {
		this.appParameter = appParameter;
	}
	
	//TODO: implement
	public JSONObject toJSON() throws JSONException{
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", appName);
		jsonObj.put("parameter", appParameter);
		jsonObj.put("appId", appId);
		return jsonObj;
	}
}
