package com.google.maps;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PhotosItem{

	@JsonProperty("photo_reference")
	private String photoReference;

	@JsonProperty("width")
	private int width;

	@JsonProperty("html_attributions")
	private List<String> htmlAttributions;

	@JsonProperty("height")
	private int height;

	public void setPhotoReference(String photoReference){
		this.photoReference = photoReference;
	}

	public String getPhotoReference(){
		return photoReference;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setHtmlAttributions(List<String> htmlAttributions){
		this.htmlAttributions = htmlAttributions;
	}

	public List<String> getHtmlAttributions(){
		return htmlAttributions;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	@Override
 	public String toString(){
		return 
			"PhotosItem{" + 
			"photo_reference = '" + photoReference + '\'' + 
			",width = '" + width + '\'' + 
			",html_attributions = '" + htmlAttributions + '\'' + 
			",height = '" + height + '\'' + 
			"}";
		}
}