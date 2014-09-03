package com.platzerworld.facebook.utils.actions;

import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.platzerworld.facebook.utils.GraphPath;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.entities.Account;
import com.platzerworld.facebook.utils.fb.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class GetAccountsAction extends GetAction<List<Account>> {

	public GetAccountsAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	@Override
	protected String getGraphPath() {
		return getTarget() + "/" + GraphPath.ACCOUNTS;
	}

	@Override
	protected List<Account> processResponse(Response response) {
		List<GraphObject> graphObjects = Utils.typedListFromResponse(response, GraphObject.class);
		List<Account> groups = new ArrayList<Account>(graphObjects.size());
		for (GraphObject graphObject : graphObjects) {
			Account group = Account.create(graphObject);
			groups.add(group);
		}
		return groups;
	}

}
