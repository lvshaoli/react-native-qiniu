package com.qiniulibrary;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceView;
import com.qiniu.droid.rtc.QNRTCEngine;
import com.qiniu.droid.rtc.QNRTCEngineEventListener;
import com.qiniu.droid.rtc.QNRTCEnv;
import com.qiniu.droid.rtc.QNRTCSetting;
import com.qiniu.droid.rtc.QNSourceType;
import com.qiniu.droid.rtc.QNSurfaceView;
import com.qiniu.droid.rtc.QNTrackInfo;
import com.qiniu.droid.rtc.QNVideoFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lvshaoli on 2019/7/27.
 */

public class VideoManager {

    public static VideoManager sVideoManager;

    public QNRTCEngine mRtcEngine;

    private Context context;

    private int mLocalUid = 0;
    public static final String TAG_CAMERA = "camera";
    public static final String TAG_SCREEN = "screen";

    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    private static final int BITRATE_FOR_SCREEN_VIDEO = (int) (1.5 * 1000 * 1000);

    private VideoManager() {
        mSurfaceViews = new SparseArray<QNSurfaceView>();
    }

    private SparseArray<QNSurfaceView> mSurfaceViews;

    public static VideoManager getInstance() {
        if (sVideoManager == null) {
            synchronized (VideoManager.class) {
                if (sVideoManager == null) {
                    sVideoManager = new VideoManager();
                }
            }
        }
        return sVideoManager;
    }


    /**
     * 初始化RtcEngine
     */
    public void init(Context context, QNRTCEngineEventListener mRtcEventHandler, QNRTCSetting options) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        mScreenWidth = outMetrics.widthPixels;
        mScreenHeight = outMetrics.heightPixels;
        QNRTCEnv.init(context); // 首先，在 Application 里，完成 SDK 的初始化操作
        this.context = context;
        //创建QNRTCEngine对象，mRtcEventHandler为RtcEngine的回调
        try {
            mRtcEngine = QNRTCEngine.createEngine(context, options, mRtcEventHandler);

//       SharedPreferences preferences = context.getSharedPreferences("亚米医疗问诊", Context.MODE_PRIVATE);
//      int videoWidth = preferences.getInt("width", 300);
//      int videoHeight = preferences.getInt("height", 400);
//      int fps = preferences.getInt("fps", 15);
//      QNVideoFormat format = new QNVideoFormat(videoWidth, videoHeight, fps);
//
//            QNTrackInfo localAudioTrack = mRtcEngine.createTrackInfoBuilder()
//                    .setSourceType(QNSourceType.AUDIO)
//                    .setBitrate(64 * 1000)// 设置音频码率
//                    .setMaster(true)
//                    .create();

        } catch (Exception e) {
            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
    }


    public List<QNTrackInfo> initLocalAudioTrack() {
        List<QNTrackInfo> mLocalTrackList = new ArrayList<>();
////        QNTrackInfo mLocalAudioTrack = mRtcEngine.createTrackInfoBuilder()
////                .setSourceType(QNSourceType.AUDIO)
////                .setMaster(true)
////                .create();
////        mLocalTrackList.add(mLocalAudioTrack);
//        QNVideoFormat screenEncodeFormat = new QNVideoFormat(mScreenWidth/2, mScreenHeight/2, 15);
         QNTrackInfo mLocalVideoTrack;
////         QNTrackInfo mLocalAudioTrack;
//         QNTrackInfo mLocalScreenTrack;
//        mLocalScreenTrack = mRtcEngine.createTrackInfoBuilder()
//                .setSourceType(QNSourceType.VIDEO_SCREEN)
//                .setVideoPreviewFormat(screenEncodeFormat)
//                .setBitrate(BITRATE_FOR_SCREEN_VIDEO)
//                .setMaster(true)
//                .setTag(TAG_SCREEN).create();
//        mLocalVideoTrack = mRtcEngine.createTrackInfoBuilder()
//                .setSourceType(QNSourceType.VIDEO_CAMERA)
//                .setTag(TAG_CAMERA).create();
//        mLocalTrackList.add(mLocalScreenTrack);
//        mLocalTrackList.add(mLocalVideoTrack);

        mLocalVideoTrack = mRtcEngine.createTrackInfoBuilder()
                .setSourceType(QNSourceType.VIDEO_CAMERA)
                .setMaster(true)
                .setTag(TAG_CAMERA).create();
        mLocalTrackList.add(mLocalVideoTrack);
        return mLocalTrackList;
    }


    /**
     * 发布本地流
     * @param mLocalTrackList
     */
    public void publishTracks(List<QNTrackInfo> mLocalTrackList) {
        mRtcEngine.publishTracks(mLocalTrackList);
    }

    /**
     * 设置本地视频，即前置摄像头预览
     */
    public VideoManager setupLocalVideo() {
        //创建一个SurfaceView用作视频预览
//        QNSurfaceView surfaceView = new QNSurfaceView(this.context);
        QNSurfaceView surfaceView = new QNSurfaceView(this.context);
        //将SurfaceView保存起来在SparseArray中，后续会将其加入界面。key为视频的用户id，这里是本地视频, 默认id是0

        mSurfaceViews.put(mLocalUid, surfaceView);


        SharedPreferences preferences = context.getSharedPreferences("亚米医疗问诊", Context.MODE_PRIVATE);
        int videoWidth = preferences.getInt("width", 300);
        int videoHeight = preferences.getInt("height", 400);
        int fps = preferences.getInt("fps", 15);
        QNVideoFormat format = new QNVideoFormat(videoWidth, videoHeight, fps);
        QNTrackInfo localVideoTrack = mRtcEngine.createTrackInfoBuilder()
                .setVideoEncodeFormat(format)
                .setSourceType(QNSourceType.VIDEO_CAMERA)
                .setBitrate(600 * 1000)// 设置视频码率
                .setMaster(true)
                .create();
        //设置本地视频，渲染模式选择VideoCanvas.RENDER_MODE_HIDDEN，如果选其他模式会出现视频不会填充满整个SurfaceView的情况，
//        mRtcEngine.setRenderWindow;
        mRtcEngine.setRenderWindow(localVideoTrack, surfaceView);
        return this;//返回AgoraManager以作链式调用
    }

    public VideoManager setupRemoteVideo(int uid) {

        QNSurfaceView surfaceView = new QNSurfaceView(this.context);
        mSurfaceViews.put(uid, surfaceView);
                    QNTrackInfo localAudioTrack = mRtcEngine.createTrackInfoBuilder()
                    .setSourceType(QNSourceType.AUDIO)
                    .setBitrate(64 * 1000)// 设置音频码率
                    .setMaster(true)
                    .create();

        mRtcEngine.setRenderWindow(localAudioTrack, surfaceView);
        return this;
    }


    /**
     * 订阅远程视频
     * @param track
     * @return
     */
    public VideoManager onSubscribedRemoteVideo(QNTrackInfo track) {
        QNSurfaceView surfaceView = this.getSurfaceView(33);
        mRtcEngine.setRenderWindow(track, surfaceView);
        return this;
    }


    /**
     * 加入房间
     * @param roomToken
     * @param uid
     * @return
     */
    public VideoManager joinRoom(String roomToken, int uid) {
        mRtcEngine.startCapture();
        mRtcEngine.joinRoom(roomToken);
        return this;
    }

    /**
     * 离开房间
     */
    public void leaveRoom() {
        if(mRtcEngine != null) {
            mRtcEngine.leaveRoom();
        }
    }

    /**
     * 销毁实例对象
     */
    public void destroryRoom () {
        mRtcEngine.destroy();
    }


    /**
     * 开启数据统计 默认间隔为3s
     * @return
     */

    public void enableStatistics() {
        mRtcEngine.enableStatistics();
    }


    public QNSurfaceView getLocalSurfaceView() {
        return mSurfaceViews.get(mLocalUid);
    }

    public QNSurfaceView getSurfaceView(int uid) {
        return mSurfaceViews.get(uid);
    }
}
