package edu.matc.pricecheck;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.math.BigDecimal;

@Generated("com.robohorse.robopojogenerator")
public class ItemsItem{

	@JsonProperty("unit")
	private String unit;

	@JsonProperty("priceDollar")
	private BigDecimal priceDollar;

	@JsonProperty("name")
	private String name;

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

	public void setPriceDollar(BigDecimal priceDollar){
		this.priceDollar = priceDollar;
	}

	public BigDecimal getPriceDollar(){
		return priceDollar;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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
			",brand = '" + brand + '\'' + 
			",unitValue = '" + unitValue + '\'' + 
			"}";
		}
}