package com.platzerworld.facebook.utils.actions;

import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.platzerworld.facebook.utils.GraphPath;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.entities.Comment;
import com.platzerworld.facebook.utils.fb.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class GetCommentsAction extends GetAction<List<Comment>> {

	public GetCommentsAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	@Override
	protected String getGraphPath() {
		return getTarget() + "/" + GraphPath.COMMENTS;
	}

	@Override
	protected List<Comment> processResponse(Response response) {
		List<GraphObject> graphObjects = Utils.typedListFromResponse(response, GraphObject.class);
		List<Comment> comments = new ArrayList<Comment>(graphObjects.size());
		for (GraphObject graphObject : graphObjects) {
			Comment comment = Comment.create(graphObject);
			comments.add(comment);
		}
		return comments;
	}

}
