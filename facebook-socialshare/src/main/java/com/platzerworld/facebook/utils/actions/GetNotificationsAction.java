package com.platzerworld.facebook.utils.actions;

import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.platzerworld.facebook.utils.GraphPath;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.entities.Notification;
import com.platzerworld.facebook.utils.fb.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class GetNotificationsAction extends GetAction<List<Notification>> {

	public GetNotificationsAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	@Override
	protected String getGraphPath() {
		return String.format("%s/%s", getTarget(), GraphPath.NOTIFICATIONS);
	}

	@Override
	protected List<Notification> processResponse(Response response) {
		List<GraphObject> graphObjects = Utils.typedListFromResponse(response, GraphObject.class);
		List<Notification> notifications = new ArrayList<Notification>(graphObjects.size());
		for (GraphObject graphObject : graphObjects) {
			Notification notification = Notification.create(graphObject);
			notifications.add(notification);
		}
		return notifications;
	}

}
