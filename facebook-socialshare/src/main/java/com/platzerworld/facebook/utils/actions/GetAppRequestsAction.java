package com.platzerworld.facebook.utils.actions;

import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.platzerworld.facebook.utils.GraphPath;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.entities.AppRequest;
import com.platzerworld.facebook.utils.fb.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class GetAppRequestsAction extends GetAction<List<AppRequest>> {

	public GetAppRequestsAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	@Override
	protected String getGraphPath() {
		return String.format("%s/%s", getTarget(), GraphPath.APPREQUESTS);
	}

	@Override
	protected List<AppRequest> processResponse(Response response) {
		List<GraphObject> graphObjects = Utils.typedListFromResponse(response, GraphObject.class);
		List<AppRequest> appRequests = new ArrayList<AppRequest>(graphObjects.size());
		for (GraphObject graphObject : graphObjects) {
			AppRequest graphRequest = AppRequest.create(graphObject);
			appRequests.add(graphRequest);
		}
		return appRequests;
	}

}
