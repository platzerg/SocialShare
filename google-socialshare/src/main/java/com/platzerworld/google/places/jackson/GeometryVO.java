package com.platzerworld.google.places.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeometryVO implements Serializable {
	
	private Map<String, String> location;

	public Map<String, String> getLocation() {
		return location;
	}

	public void setLocation(Map<String, String> location) {
		this.location = location;
	}
}
