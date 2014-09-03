package com.platzerworld.google.places.query;

import com.google.api.client.http.HttpRequest;

import java.io.IOException;

public abstract class Query {

	protected static int DEFAULT_RADIUS =2500;
	protected QueryBuilder queryBuilder = new QueryBuilder();

	public Query(String apiKey) {
		setKey(apiKey);
		setSensor(true);
	}
	
	public Query(String apiKey, boolean sensor) {
		setKey(apiKey);
		setSensor(sensor);
	}

	/**
	 * @return
	 */
	public abstract String getUrl();

	/**
	 * @param apiKey
	 */
	private Query setKey(String apiKey) {
		queryBuilder.addParameter("key", apiKey);
		return this;
	}

	/**
	 * @param sensor
	 * @return 
	 */
	public Query setSensor(boolean sensor) {
		queryBuilder.addParameter("sendor", Boolean.toString(sensor));
		return this;
	}

	/**
	 * @param language
	 */
	public Query setLanguage(String language) {
		queryBuilder.addParameter("language", language);
		return this;
	}

	/**
	 * @return
	 * @throws java.io.IOException
	 */
	public HttpRequest getRequest() throws IOException {
		return queryBuilder.generateRequest(this);
	}
	
	/**
	 * @param name
	 * @return
	 */
	protected String getParameter(String name) {
		return this.queryBuilder.getParameter(name);
	}

	/**
	 * @param name
	 * @return
	 */
	protected boolean removeParameter(String name) {
		return queryBuilder.removeParameter(name);
	}

	/**
     * 
     */
	protected void clearParameters() {
		queryBuilder.clearParameters();
	}
}
