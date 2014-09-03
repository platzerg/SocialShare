package com.platzerworld.facebook.utils.actions;

import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.platzerworld.facebook.utils.GraphPath;
import com.platzerworld.facebook.utils.Utils;
import com.platzerworld.facebook.utils.entities.Album;
import com.platzerworld.facebook.utils.fb.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class GetAlbumsAction extends GetAction<List<Album>> {

	public GetAlbumsAction(SessionManager sessionManager) {
		super(sessionManager);
	}

	@Override
	protected String getGraphPath() {
		return String.format("%s/%s", getTarget(), GraphPath.ALBUMS);
	}

	@Override
	protected List<Album> processResponse(Response response) {
		List<GraphObject> graphObjects = Utils.typedListFromResponse(response, GraphObject.class);
		List<Album> albums = new ArrayList<Album>(graphObjects.size());
		for (GraphObject graphObject : graphObjects) {
			Album album = Album.create(graphObject);
			albums.add(album);
		}
		return albums;
	}

}
