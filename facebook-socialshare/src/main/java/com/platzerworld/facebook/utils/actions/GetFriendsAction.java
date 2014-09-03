package com.platzerworld.facebook.utils.actions;

import android.os.Bundle;

import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.platzerworld.facebook.utils.GraphPath;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.entities.Profile;
import com.platzerworld.facebook.utils.entities.Profile.Properties;
import com.platzerworld.facebook.utils.fb.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class GetFriendsAction extends GetAction<List<Profile>> {

	private Properties mProperties;

	public GetFriendsAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	public void setProperties(Properties properties) {
		mProperties = properties;
	}

	@Override
	protected String getGraphPath() {
		return String.format("%s/%s", getTarget(), GraphPath.FRIENDS);
	}

	@Override
	protected Bundle getBundle() {
		if (mProperties != null) {
			return mProperties.getBundle();
		}
		return null;
	}

	@Override
	protected List<Profile> processResponse(Response response) {
		List<GraphUser> graphUsers = Utils.typedListFromResponse(response, GraphUser.class);
		List<Profile> profiles = new ArrayList<Profile>(graphUsers.size());
		for (GraphUser graphUser : graphUsers) {
			profiles.add(Profile.create(graphUser));
		}
		return profiles;
	}

}
