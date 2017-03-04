package edu.matc.pricecheck;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class QueryItem{

	@JsonProperty("item")
	private String item;

	@JsonProperty("grocery")
	private Grocery grocery;

	public void setItem(String item){
		this.item = item;
	}

	public String getItem(){
		return item;
	}

	public void setGrocery(Grocery grocery){
		this.grocery = grocery;
	}

	public Grocery getGrocery(){
		return grocery;
	}

	@Override
 	public String toString(){
		return 
			"QueryItem{" + 
			"item = '" + item + '\'' + 
			",grocery = '" + grocery + '\'' + 
			"}";
		}
}