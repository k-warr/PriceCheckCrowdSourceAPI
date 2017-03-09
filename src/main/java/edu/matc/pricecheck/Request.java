package edu.matc.pricecheck;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Request{

	@JsonProperty("output")
	private String output;

	@JsonProperty("userLatitude")
	private double userLatitude;

	@JsonProperty("radiusMile")
	private int radiusMile;

	@JsonProperty("entry")
	private List<EntryItem> entry;

	@JsonProperty("apikey")
	private String apikey;

	@JsonProperty("action")
	private String action;

	@JsonProperty("userLongtitude")
	private double userLongtitude;

	@JsonProperty("type")
	private String type;

	public void setOutput(String output){
		this.output = output;
	}

	public String getOutput(){
		return output;
	}

	public void setUserLatitude(double userLatitude){
		this.userLatitude = userLatitude;
	}

	public double getUserLatitude(){
		return userLatitude;
	}

	public void setRadiusMile(int radiusMile){
		this.radiusMile = radiusMile;
	}

	public int getRadiusMile(){
		return radiusMile;
	}

	public void setEntry(List<EntryItem> entry){
		this.entry = entry;
	}

	public List<EntryItem> getEntry(){
		return entry;
	}

	public void setApikey(String apikey){
		this.apikey = apikey;
	}

	public String getApikey(){
		return apikey;
	}

	public void setAction(String action){
		this.action = action;
	}

	public String getAction(){
		return action;
	}

	public void setUserLongtitude(double userLongtitude){
		this.userLongtitude = userLongtitude;
	}

	public double getUserLongtitude(){
		return userLongtitude;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"Request{" + 
			"output = '" + output + '\'' + 
			",userLatitude = '" + userLatitude + '\'' + 
			",radiusMile = '" + radiusMile + '\'' + 
			",entry = '" + entry + '\'' + 
			",apikey = '" + apikey + '\'' + 
			",action = '" + action + '\'' + 
			",userLongtitude = '" + userLongtitude + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}