package com.platzerworld.facebook.utils.actions;

import android.os.Bundle;

import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.platzerworld.facebook.utils.GraphPath;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.entities.Score;
import com.platzerworld.facebook.utils.fb.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class GetScoresAction extends GetAction<List<Score>> {

	public GetScoresAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	@Override
	protected String getGraphPath() {
		return String.format("%s/%s", configuration.getAppId(), GraphPath.SCORES);
	}

	@Override
	protected Bundle getBundle() {
		return null;
	}

	@Override
	protected List<Score> processResponse(Response response) {
		List<GraphObject> graphObjects = Utils.typedListFromResponse(response, GraphObject.class);
		List<Score> scores = new ArrayList<Score>(graphObjects.size());
		for (GraphObject graphObject : graphObjects) {
			Score score = Score.create(graphObject);
			scores.add(score);
		}
		return scores;
	}

}
