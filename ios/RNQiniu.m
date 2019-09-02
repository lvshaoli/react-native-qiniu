//
//  RNQiniu.m
//  RNQiniu
//
//  Created by 吕少立 on 2019/8/28.
//  Copyright © 2019年 yami. All rights reserved.
//
#import "RNQiniu.h"
#import "QNVideoConst.h"
#import "QRDNetworkUtil.h"

#define QN_USER_ID_KEY @"QN_USER_ID"
#define QN_APP_ID_KEY @"QN_APP_ID"
#define QN_SET_CONFIG_KEY @"QN_SET_CONFIG"
#define QN_ROOM_NAME_KEY @"QN_ROOM_NAME"
#define QN_RTC_DEMO_APPID @"d8lk7l4ed"

@interface RNQiniu ()
@property (strong, nonatomic) QNRTCEngine *rtcEngine;

@end


@implementation RNQiniu

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

@synthesize bridge = _bridge;

//导出常量
- (NSDictionary *)constantsToExport {
    return @{};
}

/**
 *初始化QNRTCEngine
 *
 */
//RCT_EXPORT_METHOD(init:(NSDictionary *)options) {
RCT_EXPORT_METHOD(initEngine){
//    [QNVideoConst share].appid = options[@"appid"];
    self.rtcEngine = [[QNRTCEngine alloc] init];
    self.rtcEngine.delegate = self;
    [QNVideoConst share].rtcEngine = self.rtcEngine;
    
    [self requestToken];
}

/**
 * 发布本地视频
 **/
- (void)publish {
    QNTrackInfo *audioTrack = [[QNTrackInfo alloc] initWithSourceType:QNRTCSourceTypeAudio master:YES];
    QNTrackInfo *cameraTrack =  [[QNTrackInfo alloc] initWithSourceType:(QNRTCSourceTypeCamera)
                                                                    tag:cameraTag
                                                                 master:YES
                                                             bitrateBps:600
                                                        videoEncodeSize:CGSizeMake(1400, 1400)];
    
    [self.rtcEngine publishTracks:@[audioTrack, cameraTrack]];
}

- (void)requestToken {
    __weak typeof(self) wself = self;
    [QRDNetworkUtil requestTokenWithRoomName:@"yamidaohang" appId:QN_RTC_DEMO_APPID userId:@"456" completionHandler:^(NSError *error, NSString *token) {
        
        if (error) {
            NSLog(@"请求 token 出错，请检查网络");
        } else {
          
//            wself.token = token;
            [wself localJoinRoom:token uid:0];
        }
    }];
}

-(void) localJoinRoom:(NSString *)token uid:(NSInteger)uid {
    //保存一下uid 在自定义视图使用
    [QNVideoConst share].localUid = uid;
    [self.rtcEngine joinRoomWithToken:token];
}


//加入房间
RCT_EXPORT_METHOD(joinRoom:(NSString *)token uid:(NSInteger)uid) {
    //保存一下uid 在自定义视图使用
//    [QNVideoConst share].localUid = uid;
//    [self.rtcEngine joinRoomWithToken:token];
    [self localJoinRoom:token uid:uid];
}

//离开房间
RCT_EXPORT_METHOD(leaveRoom){
    [self.rtcEngine leaveRoom];
}


#pragma mark - QNRTCEngineDelegate
/**
 * SDK 运行过程中发生错误会通过该方法回调，具体错误码的含义可以见 QNTypeDefines.h 文件
 */
- (void)RTCEngine:(QNRTCEngine *)engine didFailWithError:(NSError *)error {
    NSLog(@"---didFailWithError-%@", error);
}

/**
 * 房间状态变更的回调。当状态变为 QNRoomStateReconnecting 时，SDK 会为您自动重连，如果希望退出，直接调用 leaveRoom 即可
 */
- (void)RTCEngine:(QNRTCEngine *)engine roomStateDidChange:(QNRoomState)roomState {
    NSLog(@"---roomStateDidChange-");
    dispatch_async(dispatch_get_main_queue(), ^{
        
        if (QNRoomStateConnected == roomState) {
            NSLog(@"-----加入房间成功----");
            [self publish];
        } else if (QNRoomStateIdle == roomState) {
        
        } else if (QNRoomStateReconnecting == roomState) {
             NSLog(@"-----正在重连...----");
        } else if (QNRoomStateReconnected == roomState) {
            NSLog(@"-----重新加入房间成功----");
        }
    });
}

- (void)RTCEngine:(QNRTCEngine *)engine didPublishLocalTracks:(NSArray<QNTrackInfo *> *)tracks {
      NSLog(@"---didPublishLocalTracks-");
}

/**
 * 远端用户取消发布音/视频的回调
 */
- (void)RTCEngine:(QNRTCEngine *)engine didUnPublishTracks:(NSArray<QNTrackInfo *> *)tracks ofRemoteUserId:(NSString *)userId {
     NSLog(@"---didUnPublishTracks-");
}

/**
 * 被 userId 踢出的回调
 */
- (void)RTCEngine:(QNRTCEngine *)engine didKickoutByUserId:(NSString *)userId {
      NSLog(@"---didKickoutByUserId-");
}

- (void)RTCEngine:(QNRTCEngine *)engine didSubscribeTracks:(NSArray<QNTrackInfo *> *)tracks ofRemoteUserId:(NSString *)userId {
    NSLog(@"---didSubscribeTracks-");
//        QRDUserView *userView = [QNVideoConst share].qNVideoView;
    dispatch_async(dispatch_get_main_queue(), ^{
        
        for (QNTrackInfo *trackInfo in tracks) {

//                RNQinniuVideoView *remoteView = [RNQinniuVideoView shareView];
//            NSLog(@"++++++++++++++%@-----%@", trackInfo.trackId, remoteView);
        }
    });
}




/**
 * 远端用户视频首帧解码后的回调，如果需要渲染，则需要返回一个带 renderView 的 QNVideoRender 对象
 */
- (QNVideoRender *)RTCEngine:(QNRTCEngine *)engine firstVideoDidDecodeOfTrackId:(NSString *)trackId remoteUserId:(NSString *)userId {
      NSLog(@"---firstVideoDidDecodeOfTrackId-");
    RNQinniuVideoView *remoteView = [QNVideoConst share].qinniuVideoView;
    QNVideoView *videoView = [remoteView RemoteView];
    
    QNVideoRender *render = [[QNVideoRender alloc] init];
    videoView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
    [remoteView insertSubview:videoView atIndex:0];
    render.renderView = videoView;

    
    
    // 设置l预览窗口的大小
    CGRect rx = [ UIScreen mainScreen ].bounds;
    CGFloat width = rx.size.width;
    [QNVideoConst share].rtcEngine.previewView.frame = CGRectMake(width - 180, 50, 150, 150);
    
    
//    render = [remoteView RemoteURender];
//    if(render == nil) {
//        render = [[QNVideoRender alloc] init];
//         QNVideoView *videoView = [[QNVideoView alloc] initWithFrame:CGRectMake(10, 10, 300, 300)];
////         videoView.contentMode = UIViewContentModeScaleAspectFit;;
//        [remoteView addSubview:videoView];
//        render.renderView = videoView;
//
//    }
    return render;
}

/**
 * 远端用户视频取消渲染到 renderView 上的回调
 */
- (void)RTCEngine:(QNRTCEngine *)engine didDetachRenderView:(UIView *)renderView ofTrackId:(NSString *)trackId remoteUserId:(NSString *)userId {
      NSLog(@"---didDetachRenderView-");
    
}
/**
 * 远端用户音频状态变更为 muted 的回调
 */
- (void)RTCEngine:(QNRTCEngine *)engine didAudioMuted:(BOOL)muted ofTrackId:(NSString *)trackId byRemoteUserId:(NSString *)userId {
     NSLog(@"---didAudioMuted-");
}


/**
 * 远端用户视频状态变更为 muted 的回调
 */
- (void)RTCEngine:(QNRTCEngine *)engine didVideoMuted:(BOOL)muted ofTrackId:(NSString *)trackId byRemoteUserId:(NSString *)userId {
      NSLog(@"---didVideoMuted-");
}
@end
  