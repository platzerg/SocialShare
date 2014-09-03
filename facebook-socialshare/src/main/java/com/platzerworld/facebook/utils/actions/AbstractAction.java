package com.platzerworld.facebook.utils.actions;

import com.platzerworld.facebook.utils.fb.SessionManager;
import com.platzerworld.facebook.utils.fb.SimpleFacebook;
import com.platzerworld.facebook.utils.fb.SimpleFacebookConfiguration;

public abstract class AbstractAction {

	protected SessionManager sessionManager;
	protected SimpleFacebookConfiguration configuration = SimpleFacebook.getConfiguration();

	public AbstractAction(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public void execute() {
		executeImpl();
	}

	protected abstract void executeImpl();
}
