package com.platzerworld.facebook.utils.actions;

import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.platzerworld.facebook.utils.GraphPath;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.entities.Checkin;
import com.platzerworld.facebook.utils.fb.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class GetCheckinsAction extends GetAction<List<Checkin>> {

	public GetCheckinsAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	@Override
	protected String getGraphPath() {
		return String.format("%s/%s", getTarget(), GraphPath.CHECKINS);
	}

	@Override
	protected List<Checkin> processResponse(Response response) {
		List<GraphObject> graphObjects = Utils.typedListFromResponse(response, GraphObject.class);
		List<Checkin> checkins = new ArrayList<Checkin>(graphObjects.size());
		for (GraphObject graphObject : graphObjects) {
			Checkin checkin = Checkin.create(graphObject);
			checkins.add(checkin);
		}
		return checkins;
	}

}
