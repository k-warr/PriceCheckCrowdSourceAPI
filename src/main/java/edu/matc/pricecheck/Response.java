package edu.matc.pricecheck;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Response{

	@JsonProperty("request")
	private Request request;

	public void setRequest(Request request){
		this.request = request;
	}

	public Request getRequest(){
		return request;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"request = '" + request + '\'' + 
			"}";
		}
}