package com.platzerworld.facebook.utils.actions;

import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.platzerworld.facebook.utils.GraphPath;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.entities.Photo;
import com.platzerworld.facebook.utils.fb.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class GetPhotosAction extends GetAction<List<Photo>> {

	public GetPhotosAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	@Override
	protected String getGraphPath() {
		return getTarget() + "/" + GraphPath.PHOTOS;
	}

	@Override
	protected List<Photo> processResponse(Response response) {
		List<GraphObject> graphObjects = Utils.typedListFromResponse(response, GraphObject.class);
		List<Photo> photos = new ArrayList<Photo>(graphObjects.size());
		for (GraphObject graphObject : graphObjects) {
			Photo photo = Photo.create(graphObject);
			photos.add(photo);
		}
		return photos;
	}

}
