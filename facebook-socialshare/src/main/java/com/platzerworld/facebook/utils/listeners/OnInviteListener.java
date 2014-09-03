package com.platzerworld.facebook.utils.listeners;

import java.util.List;

public interface OnInviteListener extends OnErrorListener {
    void onComplete(List<String> invitedFriends, String requestId);

    void onCancel();
}
