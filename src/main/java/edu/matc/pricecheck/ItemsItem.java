package edu.matc.pricecheck;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ItemsItem{

	@JsonProperty("unit")
	private String unit;

	@JsonProperty("priceDollar")
	private double priceDollar;

	@JsonProperty("name")
	private String name;

	@JsonProperty("type")
	private String type;

	@JsonProperty("brand")
	private String brand;

	@JsonProperty("unitValue")
	private int unitValue;

	public void setUnit(String unit){
		this.unit = unit;
	}

	public String getUnit(){
		return unit;
	}

	public void setPriceDollar(double priceDollar){
		this.priceDollar = priceDollar;
	}

	public double getPriceDollar(){
		return priceDollar;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}

	public String getBrand(){
		return brand;
	}

	public void setUnitValue(int unitValue){
		this.unitValue = unitValue;
	}

	public int getUnitValue(){
		return unitValue;
	}

	@Override
 	public String toString(){
		return 
			"ItemsItem{" + 
			"unit = '" + unit + '\'' + 
			",priceDollar = '" + priceDollar + '\'' + 
			",name = '" + name + '\'' + 
			",type = '" + type + '\'' + 
			",brand = '" + brand + '\'' + 
			",unitValue = '" + unitValue + '\'' + 
			"}";
		}
}