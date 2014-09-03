package com.platzerworld.facebook.utils.actions;

import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.platzerworld.facebook.utils.GraphPath;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.entities.Group;
import com.platzerworld.facebook.utils.fb.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class GetGroupsAction extends GetAction<List<Group>> {

	public GetGroupsAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	@Override
	protected String getGraphPath() {
		return getTarget() + "/" + GraphPath.GROUPS;
	}

	@Override
	protected List<Group> processResponse(Response response) {
		List<GraphObject> graphObjects = Utils.typedListFromResponse(response, GraphObject.class);
		List<Group> groups = new ArrayList<Group>(graphObjects.size());
		for (GraphObject graphObject : graphObjects) {
			Group group = Group.create(graphObject);
			groups.add(group);
		}
		return groups;
	}

}
