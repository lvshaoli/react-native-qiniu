/**
 * @Author: lvshaoli
 * @DATE: 2019-09-02
 * @TIME: 09:27
 * @DESC: '七牛云实时视频sdk封装'
 * */


import { NativeModules, Platform } from 'react-native';

const { RNQiniu } = NativeModules;


class QNVideoModule {

    /**
     * 初始化视频引擎，在加载UI之前完成调用
     */
    initRtcEngine() {
        RNQiniu.initEngine();
    }

    /**
     * 加入房间
     * @param roomToken
     * @param uid
     */
    joinRoom(roomToken, uid) {
        RNQiniu.joinRoom(roomToken, uid);
    }

    /**
     * 离开房间
     */
    leaveRoom() {
        RNQiniu.leaveRoom();
    }
}
