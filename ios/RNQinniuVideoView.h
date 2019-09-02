//
//  RNQinniuVideoView.h
//  RNQiniu
//
//  Created by 亚米 on 2019/8/28.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "QNVideoConst.h"
NS_ASSUME_NONNULL_BEGIN

@interface RNQinniuVideoView : UIView
@property (strong, nonatomic) QNRTCEngine *rtcEngine;

@property (nonatomic) BOOL showLocalVideo;
@property (nonatomic) NSInteger remoteUid;
@property (nonatomic) QNVideoRender *render;
-(QNVideoView *)RemoteView;
//+ (instancetype)shareView;
@end

NS_ASSUME_NONNULL_END
