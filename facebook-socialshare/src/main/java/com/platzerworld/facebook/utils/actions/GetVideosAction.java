package com.platzerworld.facebook.utils.actions;

import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.platzerworld.facebook.utils.GraphPath;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.entities.Video;
import com.platzerworld.facebook.utils.fb.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class GetVideosAction extends GetAction<List<Video>> {

	public GetVideosAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	@Override
	protected String getGraphPath() {
		return getTarget() + "/" + GraphPath.VIDEOS;
	}

	@Override
	protected List<Video> processResponse(Response response) {
		List<GraphObject> graphObjects = Utils.typedListFromResponse(response, GraphObject.class);
		List<Video> videos = new ArrayList<Video>(graphObjects.size());
		for (GraphObject graphObject : graphObjects) {
			Video video = Video.create(graphObject);
			videos.add(video);
		}
		return videos;
	}

}
