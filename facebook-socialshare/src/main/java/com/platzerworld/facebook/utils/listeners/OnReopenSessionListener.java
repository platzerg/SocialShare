package com.platzerworld.facebook.utils.listeners;

import com.platzerworld.facebook.utils.fb.Permission;


public interface OnReopenSessionListener {
    void onSuccess();

    void onNotAcceptingPermissions(Permission.Type type);
}
