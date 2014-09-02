package com.platzerworld.google.places.query;

import android.location.Location;

public class TextSearchQuery extends SearchQuery {

	public TextSearchQuery(String apiKey, String query, boolean sensor) {
		super(apiKey);
		setQuery(query);
	}

	/*
	 * Set location and set required parameter "radius" to default value 2500.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.a2plab.googleplaces.query.SearchQuery#setLocation(double,
	 * double)
	 */
	@Override
	public TextSearchQuery setLocation(double latitude, double longitude) {
		super.setLocation(latitude, longitude);
		if (queryBuilder.getParameter("radius") == null) {
			setRadius(DEFAULT_RADIUS);
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.a2plab.googleplaces.query.SearchQuery#setLocation(android.location
	 * .Location)
	 */
	@Override
	public TextSearchQuery setLocation(Location location) {
		return this.setLocation(location.getLatitude(), location.getLongitude());
	}

	public void setQuery(String query) {
		queryBuilder.addParameter("query", query);
	}

	@Override
	public String getUrl() {
		return "https://maps.googleapis.com/maps/api/place/textsearch/json?";
	}
}
