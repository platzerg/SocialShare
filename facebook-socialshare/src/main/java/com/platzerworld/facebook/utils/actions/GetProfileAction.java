package com.platzerworld.facebook.utils.actions;

import android.os.Bundle;

import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.platzerworld.facebook.utils.entities.Profile;
import com.platzerworld.facebook.utils.entities.Profile.Properties;
import com.platzerworld.facebook.utils.fb.SessionManager;

public class GetProfileAction extends GetAction<Profile> {

	private Properties mProperties;

	public GetProfileAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	public void setProperties(Properties properties) {
		mProperties = properties;
	}

	@Override
	protected String getGraphPath() {
		return getTarget();
	}

	@Override
	protected Bundle getBundle() {
		if (mProperties != null) {
			return mProperties.getBundle();
		}
		return null;
	}

	@Override
	protected Profile processResponse(Response response) {
		GraphUser graphUser = response.getGraphObjectAs(GraphUser.class);
		Profile profile = Profile.create(graphUser);
		return profile;
	}

}
