package com.google.maps;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Geometry{

	@JsonProperty("viewport")
	private Viewport viewport;

	@JsonProperty("location")
	private Location location;

	public void setViewport(Viewport viewport){
		this.viewport = viewport;
	}

	public Viewport getViewport(){
		return viewport;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	@Override
 	public String toString(){
		return 
			"Geometry{" + 
			"viewport = '" + viewport + '\'' + 
			",location = '" + location + '\'' + 
			"}";
		}
}