package com.platzerworld.facebook.utils.listeners;

public interface OnErrorListener {
    void onException(Throwable throwable);

    void onFail(String reason);
}