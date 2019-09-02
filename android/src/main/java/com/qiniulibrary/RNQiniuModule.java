
package com.qiniulibrary;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.qiniu.droid.rtc.QNCustomMessage;
import com.qiniu.droid.rtc.QNRTCEngine;
import com.qiniu.droid.rtc.QNRTCEngineEventListener;
import com.qiniu.droid.rtc.QNRTCSetting;
import com.qiniu.droid.rtc.QNRoomState;
import com.qiniu.droid.rtc.QNSourceType;
import com.qiniu.droid.rtc.QNStatisticsReport;
import com.qiniu.droid.rtc.QNTrackInfo;
import com.qiniu.droid.rtc.QNTrackKind;
import com.qiniu.droid.rtc.model.QNAudioDevice;
import com.qiniulibrary.utils.Config;
import com.qiniulibrary.utils.QNAppServer;

import java.util.ArrayList;
import java.util.List;

/**
 * create by lvshaoli 2019/8/27
 */

public class RNQiniuModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private Toast mLogToast;
  private String mRoomId;
  private List<QNTrackInfo> mLocalTrackList;
  private QNTrackInfo mLocalAudioTrack;
  private int mCaptureMode = Config.CAMERA_CAPTURE;

  private static final String TAG = "RNQiniuModule";

  public RNQiniuModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNQiniu";
  }


  private void logAndToast(final String msg) {
    Log.d(TAG, msg);
    if (mLogToast != null) {
      mLogToast.cancel();
    }
    mLogToast = Toast.makeText(getCurrentActivity(), msg, Toast.LENGTH_LONG);
    mLogToast.show();
  }


  private QNRTCEngineEventListener mRtcEventHandler = new QNRTCEngineEventListener() {
    @Override
    public void onRoomStateChanged(QNRoomState qnRoomState) {
      Log.i(TAG, "onRoomStateChanged:" + qnRoomState.name());
      switch (qnRoomState) {
        case RECONNECTING:
          logAndToast(getReactApplicationContext().getString(R.string.reconnecting_to_room));
          break;
        case CONNECTED:
          VideoManager.getInstance().publishTracks(mLocalTrackList);
          logAndToast(getReactApplicationContext().getString(R.string.connected_to_room));
          break;
        case RECONNECTED:
          logAndToast(getReactApplicationContext().getString(R.string.connected_to_room));
          break;
        case CONNECTING:
          logAndToast(getReactApplicationContext().getString(R.string.connecting_to, mRoomId));
          break;
      }
    }

    @Override
    public void onRemoteUserJoined(String s, String s1) {
      Log.i("---onRemoteUser-----", s + "" + s1);
    }

    @Override
    public void onRemoteUserLeft(String s) {
      Log.i("---onRemoterLeft-----", s + "");
    }

    @Override
    public void onLocalPublished(List<QNTrackInfo> list) {
      Log.d(TAG, list.size() + "");
      VideoManager.getInstance().enableStatistics();
    }

    @Override
    public void onRemotePublished(String s, List<QNTrackInfo> list) {
      Log.i("onRemotePublishe", s + "" + list.size());
    }

    @Override
    public void onRemoteUnpublished(String s, List<QNTrackInfo> list) {
      Log.i("---RemoteUnpublish-----", s + "" + list.size());
    }

    @Override
    public void onRemoteUserMuted(String s, List<QNTrackInfo> list) {
      Log.i("---onRemoterLeft-----", s + "" + list.size());
    }

    @Override
    public void onSubscribed(String s, List<QNTrackInfo> trackInfoList) {
      /**
       * 当订阅 Track 成功时会触发此回调
       */
      for(QNTrackInfo track : trackInfoList) {
        if (track.getTrackKind().equals(QNTrackKind.VIDEO)) {
         VideoManager.getInstance().onSubscribedRemoteVideo(track);
        }
      }

    }

    @Override
    public void onKickedOut(String s) {

    }

    @Override
    public void onStatisticsUpdated(QNStatisticsReport qnStatisticsReport) {

    }

    @Override
    public void onAudioRouteChanged(QNAudioDevice qnAudioDevice) {

    }

    @Override
    public void onCreateMergeJobSuccess(String s) {

    }

    @Override
    public void onError(int i, String s) {

    }

    @Override
    public void onMessageReceived(QNCustomMessage qnCustomMessage) {

    }
  };

  private void initLocal() {
    mLocalTrackList = VideoManager.getInstance().initLocalAudioTrack();
  }

  @ReactMethod
  public void initEngine() {
    QNRTCSetting options = new QNRTCSetting();
    options.setDefaultAudioRouteToSpeakerphone(true); // 默认扬声器
    VideoManager.getInstance().init(getReactApplicationContext(), mRtcEventHandler, options);

    this.initLocal();
  }

  //进入房间
  @ReactMethod
  public void joinRoom(String roomToken, int uid) {
    this.mRoomId = uid + "";
    String testToken = QNAppServer.getInstance().requestRoomToken(getReactApplicationContext(), "123","123");
    VideoManager.getInstance().joinRoom(testToken, uid);
  }

  //退出
  @ReactMethod
  public void leaveRoom() {
//    VideoManager.getInstance().stopPreview();
    VideoManager.getInstance().leaveRoom();
  }

  /**
   * 初始化请求云视频sdk
   */
  @ReactMethod
  public void initQNRTCEnv() {
//    QNRTCEnv.init(this.reactContext);
//    mEngine = QNRTCEngine.createEngine(getReactApplicationContext(), this);
//    QNSurfaceView mLocalWindow = VideoViewManager.InstaceView(VideoViewManager.surQnViw ,this.reactContext);
////      mEngine.setEventListener(/*QNRTCEngineEventListener*/ listener);
//      SharedPreferences preferences = reactContext.getSharedPreferences("会议", Context.MODE_PRIVATE);
//      int videoWidth = preferences.getInt("width", 300);
//      int videoHeight = preferences.getInt("height", 400);
//      int fps = preferences.getInt("fps", 15);
//      QNVideoFormat format = new QNVideoFormat(videoWidth, videoHeight, fps);
//      QNTrackInfo localVideoTrack = mEngine.createTrackInfoBuilder()
//              .setVideoEncodeFormat(format)
//              .setSourceType(QNSourceType.VIDEO_CAMERA)
//              .setBitrate(600 * 1000)// 设置视频码率
//              .setMaster(true)
//              .create();
//      QNTrackInfo localAudioTrack = mEngine.createTrackInfoBuilder()
//              .setSourceType(QNSourceType.AUDIO)
//              .setBitrate(64 * 1000)// 设置音频码率
//              .setMaster(true)
//              .create();
//      mEngine.setRenderWindow(localVideoTrack, mLocalWindow);
  }

}
