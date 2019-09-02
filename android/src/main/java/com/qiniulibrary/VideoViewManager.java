package com.qiniulibrary;

import android.widget.FrameLayout;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.qiniu.droid.rtc.QNSurfaceView;
import com.qiniulibrary.ui.LocalVideoView;

/**
 * Created by lvshaoli on 2019/8/27.
 */

public class VideoViewManager extends ViewGroupManager<LocalVideoView> {
    ReactApplicationContext context;

    public QNSurfaceView qnSurfaceView;

    @Override
    public String getName() {
        return "VideoViewManager";
    }

    public VideoViewManager( ReactApplicationContext context ) {
        this.context = context;
    }

    @Override
    protected LocalVideoView createViewInstance(ThemedReactContext reactContext) {
        return new LocalVideoView(reactContext);
    }

    @ReactProp(name = "showLocalVideo")
    public void setShowLocalVideo(final LocalVideoView localVideoView, boolean showLocalVideo) {
        VideoManager.getInstance().setupLocalVideo();
        qnSurfaceView = VideoManager.getInstance().getLocalSurfaceView();
//        qnSurfaceView
        localVideoView.addView(qnSurfaceView);
    }

    @ReactProp(name = "zOrderMediaOverlay")
    public void setZOrderMediaOverlay(final LocalVideoView localVideoView, boolean zOrderMediaOverlay) {
        qnSurfaceView.setZOrderMediaOverlay(zOrderMediaOverlay);
    }
    @ReactProp(name = "remoteUid")
    public void setRemoteUid(final LocalVideoView localVideoView, int remoteUid) {
        VideoManager.getInstance().setupRemoteVideo(remoteUid);
        qnSurfaceView = VideoManager.getInstance().getSurfaceView(remoteUid);
        localVideoView.addView(qnSurfaceView);
    }

}


