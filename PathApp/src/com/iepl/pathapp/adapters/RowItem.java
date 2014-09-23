package com.iepl.pathapp.adapters;

/**
 * The Class RowItem.
 */
public class RowItem {
	
	//{{private members
	
	private String title;
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	private String description;
	
	//}}
	
	public RowItem(String title, String description)
	{
		this.title = title;
		this.description = description;
	}
	
	
}
