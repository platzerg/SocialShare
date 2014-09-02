package com.platzerworld.google.places.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingAddressVO implements Serializable {
	
	private String formatted_address;
	private GeometryVO geometry;
	
	public String getFormatted_address() {
		return formatted_address;
	}
	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
	
		public GeometryVO getGeometry() {
		return geometry;
	}
	public void setGeometry(GeometryVO geometry) {
		this.geometry = geometry;
	}
}
