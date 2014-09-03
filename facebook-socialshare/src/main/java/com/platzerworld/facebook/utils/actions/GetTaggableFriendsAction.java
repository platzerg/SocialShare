package com.platzerworld.facebook.utils.actions;

import com.platzerworld.facebook.utils.GraphPath;
import com.platzerworld.facebook.utils.fb.SessionManager;

public class GetTaggableFriendsAction  extends GetFriendsAction {

	public GetTaggableFriendsAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	@Override
	protected String getGraphPath() {
		return String.format("%s/%s", getTarget(), GraphPath.TAGGABLE_FRIENDS);
	}

}
