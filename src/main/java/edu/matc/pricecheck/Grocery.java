package edu.matc.pricecheck;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Grocery{

	@JsonProperty("address")
	private String address;

	@JsonProperty("latitude")
	private double latitude;

	@JsonProperty("name")
	private String name;

	@JsonProperty("longtitude")
	private double longtitude;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLongtitude(double longtitude){
		this.longtitude = longtitude;
	}

	public double getLongtitude(){
		return longtitude;
	}

	@Override
 	public String toString(){
		return 
			"Grocery{" + 
			"address = '" + address + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",name = '" + name + '\'' + 
			",longtitude = '" + longtitude + '\'' + 
			"}";
		}
}