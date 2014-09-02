package com.platzerworld.google.places.result;

import com.google.api.client.util.Key;
import com.platzerworld.google.places.models.Place;
import com.platzerworld.google.places.models.PlaceDetails;

import java.util.ArrayList;
import java.util.List;

public class PlaceDetailsResult extends Result {

	@Key
	public PlaceDetails result;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.a2plab.googleplaces.result.Result#getResult()
	 */
	@Override
	public List<? extends Place> getResults() {
		List<PlaceDetails> detailsList = new ArrayList<PlaceDetails>();
		detailsList.add(result);
		return detailsList;
	}

	public PlaceDetails getDetails() {
		return this.result;
	}

}
