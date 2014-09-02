package com.platzerworld.google.places.result;

import com.google.api.client.util.Key;
import com.platzerworld.google.places.models.Place;

import java.util.List;

public class PlacesResult extends Result {

	@Key
	public List<? extends Place> results;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.a2plab.googleplaces.result.Result#getResult()
	 */
	@Override
	public List<? extends Place> getResults() {
		return results;
	}

}
