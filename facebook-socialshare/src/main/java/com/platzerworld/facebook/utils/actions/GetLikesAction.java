package com.platzerworld.facebook.utils.actions;

import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.platzerworld.facebook.utils.GraphPath;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.entities.Like;
import com.platzerworld.facebook.utils.fb.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class GetLikesAction extends GetAction<List<Like>> {

	public GetLikesAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	@Override
	protected String getGraphPath() {
		return getTarget() + "/" + GraphPath.LIKES;
	}

	@Override
	protected List<Like> processResponse(Response response) {
		List<GraphObject> graphObjects = Utils.typedListFromResponse(response, GraphObject.class);
		List<Like> likes = new ArrayList<Like>(graphObjects.size());
		for (GraphObject graphObject : graphObjects) {
			Like like = Like.create(graphObject);
			likes.add(like);
		}
		return likes;
	}
}
