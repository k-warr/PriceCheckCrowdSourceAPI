package edu.matc.pricecheck;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class EntryItem{

	@JsonProperty("grocery")
	private Grocery grocery;

	@JsonProperty("items")
	private List<ItemsItem> items;

	public void setGrocery(Grocery grocery){
		this.grocery = grocery;
	}

	public Grocery getGrocery(){
		return grocery;
	}

	public void setItems(List<ItemsItem> items){
		this.items = items;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"EntryItem{" + 
			"grocery = '" + grocery + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}