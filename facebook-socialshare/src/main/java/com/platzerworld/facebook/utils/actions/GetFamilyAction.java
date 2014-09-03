package com.platzerworld.facebook.utils.actions;

import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.platzerworld.facebook.utils.GraphPath;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.entities.FamilyUser;
import com.platzerworld.facebook.utils.fb.SessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sromku
 * @see https://developers.facebook.com/docs/graph-api/reference/user/family/
 */
public class GetFamilyAction extends GetAction<List<FamilyUser>> {

	public GetFamilyAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	@Override
	protected String getGraphPath() {
		return getTarget() + "/" + GraphPath.FAMILY;
	}

	@Override
	protected List<FamilyUser> processResponse(Response response) {
		List<GraphObject> graphObjects = Utils.typedListFromResponse(response, GraphObject.class);
		List<FamilyUser> familyUsers = new ArrayList<FamilyUser>(graphObjects.size());
		for (GraphObject graphObject : graphObjects) {
			FamilyUser familyUser = FamilyUser.create(graphObject);
			familyUsers.add(familyUser);
		}
		return familyUsers;
	}
}
