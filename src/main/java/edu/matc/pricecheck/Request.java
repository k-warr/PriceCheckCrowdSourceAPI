package edu.matc.pricecheck;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Request{

	@JsonProperty("query")
	private List<QueryItem> query;

	@JsonProperty("action")
	private String action;

	@JsonProperty("type")
	private String type;

	@JsonProperty("outputFormat")
	private String outputFormat;

	public void setQuery(List<QueryItem> query){
		this.query = query;
	}

	public List<QueryItem> getQuery(){
		return query;
	}

	public void setAction(String action){
		this.action = action;
	}

	public String getAction(){
		return action;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setOutputFormat(String outputFormat){
		this.outputFormat = outputFormat;
	}

	public String getOutputFormat(){
		return outputFormat;
	}

	@Override
 	public String toString(){
		return 
			"Request{" + 
			"query = '" + query + '\'' + 
			",action = '" + action + '\'' + 
			",type = '" + type + '\'' + 
			",outputFormat = '" + outputFormat + '\'' + 
			"}";
		}
}