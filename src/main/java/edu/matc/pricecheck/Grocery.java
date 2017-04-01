package edu.matc.pricecheck;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.math.BigDecimal;

@Generated("com.robohorse.robopojogenerator")
public class Grocery{

	@JsonProperty("latitude")
	private BigDecimal latitude;

	@JsonProperty("name")
	private String name;

	@JsonProperty("longtitude")
	private BigDecimal longtitude;


	/**
	 * Instantiates a new Grocery.
	 */
	public Grocery() {
	}

	/**
	 * Instantiates a new Grocery.
	 *
	 * @param latitude   the latitude
	 * @param name       the name
	 * @param longtitude the longtitude
	 */
	public Grocery(BigDecimal latitude, String name, BigDecimal longtitude) {
		this.latitude = latitude;
		this.name = name;
		this.longtitude = longtitude;
	}

	public void setLatitude(BigDecimal latitude){
		this.latitude = latitude;
	}

	public BigDecimal getLatitude(){
		return latitude;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLongtitude(BigDecimal longtitude){
		this.longtitude = longtitude;
	}

	public BigDecimal getLongtitude(){
		return longtitude;
	}

	@Override
 	public String toString(){
		return 
			"Grocery{" + 
			"latitude = '" + latitude + '\'' + 
			",name = '" + name + '\'' + 
			",longtitude = '" + longtitude + '\'' + 
			"}";
		}
}