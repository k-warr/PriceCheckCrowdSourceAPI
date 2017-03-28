package com.google.maps;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ResultsItem{

	@JsonProperty("types")
	private List<String> types;

	@JsonProperty("icon")
	private String icon;

	@JsonProperty("rating")
	private double rating;

	@JsonProperty("photos")
	private List<PhotosItem> photos;

	@JsonProperty("reference")
	private String reference;

	@JsonProperty("price_level")
	private int priceLevel;

	@JsonProperty("scope")
	private String scope;

	@JsonProperty("name")
	private String name;

	@JsonProperty("opening_hours")
	private OpeningHours openingHours;

	@JsonProperty("geometry")
	private Geometry geometry;

	@JsonProperty("vicinity")
	private String vicinity;

	@JsonProperty("id")
	private String id;

	@JsonProperty("place_id")
	private String placeId;

	public void setTypes(List<String> types){
		this.types = types;
	}

	public List<String> getTypes(){
		return types;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setRating(double rating){
		this.rating = rating;
	}

	public double getRating(){
		return rating;
	}

	public void setPhotos(List<PhotosItem> photos){
		this.photos = photos;
	}

	public List<PhotosItem> getPhotos(){
		return photos;
	}

	public void setReference(String reference){
		this.reference = reference;
	}

	public String getReference(){
		return reference;
	}

	public void setPriceLevel(int priceLevel){
		this.priceLevel = priceLevel;
	}

	public int getPriceLevel(){
		return priceLevel;
	}

	public void setScope(String scope){
		this.scope = scope;
	}

	public String getScope(){
		return scope;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setOpeningHours(OpeningHours openingHours){
		this.openingHours = openingHours;
	}

	public OpeningHours getOpeningHours(){
		return openingHours;
	}

	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}

	public Geometry getGeometry(){
		return geometry;
	}

	public void setVicinity(String vicinity){
		this.vicinity = vicinity;
	}

	public String getVicinity(){
		return vicinity;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPlaceId(String placeId){
		this.placeId = placeId;
	}

	public String getPlaceId(){
		return placeId;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItem{" + 
			"types = '" + types + '\'' + 
			",icon = '" + icon + '\'' + 
			",rating = '" + rating + '\'' + 
			",photos = '" + photos + '\'' + 
			",reference = '" + reference + '\'' + 
			",price_level = '" + priceLevel + '\'' + 
			",scope = '" + scope + '\'' + 
			",name = '" + name + '\'' + 
			",opening_hours = '" + openingHours + '\'' + 
			",geometry = '" + geometry + '\'' + 
			",vicinity = '" + vicinity + '\'' + 
			",id = '" + id + '\'' + 
			",place_id = '" + placeId + '\'' + 
			"}";
		}
}