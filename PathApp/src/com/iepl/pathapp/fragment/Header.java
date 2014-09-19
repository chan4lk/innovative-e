package com.iepl.pathapp.fragment;

public class Header {
	
	private  String name;
	private  String description;
	private  float rating;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return the rating
	 */
	public float getRating() {
		return rating;
	}
	public Header(String name, String description, float rating) {
		super();
		this.name = name;
		this.description = description;
		this.rating = rating;
	}
	
	
}