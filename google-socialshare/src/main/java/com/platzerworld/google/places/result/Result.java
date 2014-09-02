package com.platzerworld.google.places.result;

import com.google.api.client.util.Key;
import com.platzerworld.google.places.models.GooglePlaceBase;

import java.util.List;

/**
 * @author Giuseppe Mastroeni - aka: Kataklisma E-Mail: m.giuseppe@a2plab.com
 * 
 */
public abstract class Result {

	@Key
	private String status = "";

	public enum StatusCode {
		OK, ZeroResults, OverQueryLimit, RequestDenied, InvalidRequest, UnknownError, NotSupported
	}

	public static final String STATUS_CODE_OK = "OK";
	public static final String STATUS_CODE_ZERO_RESULTS = "ZERO_RESULTS";
	public static final String STATUS_CODE_OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";
	public static final String STATUS_CODE_REQUEST_DENIED = "REQUEST_DENIED";
	public static final String STATUS_CODE_INVALID_REQUEST = "INVALID_REQUEST";
	public static final String STATUS_CODE_UNKNOWN_ERROR = "UNKNOWN_ERROR";

	/**
	 * @return
	 */
	public StatusCode getStatusCode() {
		return getStatusCodeFromValue(status);
	}

	/**
	 * @param statusCodeValue
	 * @return
	 */
	protected StatusCode getStatusCodeFromValue(String statusCodeValue) {
		if (statusCodeValue.equals(STATUS_CODE_OK)) {
			return StatusCode.OK;
		} else if (statusCodeValue.equals(STATUS_CODE_ZERO_RESULTS)) {
			return StatusCode.ZeroResults;
		} else if (statusCodeValue.equals(STATUS_CODE_OVER_QUERY_LIMIT)) {
			return StatusCode.OverQueryLimit;
		} else if (statusCodeValue.equals(STATUS_CODE_REQUEST_DENIED)) {
			return StatusCode.RequestDenied;
		} else if (statusCodeValue.equals(STATUS_CODE_INVALID_REQUEST)) {
			return StatusCode.InvalidRequest;
		} else if (statusCodeValue.equals(STATUS_CODE_UNKNOWN_ERROR)) {
			return StatusCode.UnknownError;
		} else {
			return StatusCode.NotSupported;
		}
	}

	/**
	 * @return
	 */
	public abstract List<? extends GooglePlaceBase> getResults();

}
