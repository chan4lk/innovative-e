package com.iepl.pathapp.model;

/**
 * The Class Header.
 */
public class Header {
	
	/** The name. */
	private final String name;
	
	/** The description. */
	private final String description;
	
	/** The rating. */
	private final float rating;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public float getRating() {
		return rating;
	}
	
	/**
	 * Instantiates a new header.
	 *
	 * @param name the name
	 * @param description the description
	 * @param rating the rating
	 */
	public Header(final String name, final String description, final float rating) {
		super();
		this.name = name;
		this.description = description;
		this.rating = rating;
	}
	
	
}
