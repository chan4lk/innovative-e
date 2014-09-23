package com.iepl.pathapp.event;

import com.iepl.pathapp.model.Header;

public class LocationChangeEvent {
	private Header header;
	public LocationChangeEvent(Header header)
	{
		this.header = header;
	}
	
	public Header getHeader()
	{
		return this.header;
	}
}
