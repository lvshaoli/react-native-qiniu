//
//  QNVideoConst.h
//  RNQiniu
//
//  Created by 亚米 on 2019/8/28.
//  Copyright © 2019 Facebook. All rights reserved.
//


#import <Foundation/Foundation.h>

#import <QNRTCKit/QNRTCKit.h>
#import "RNQinniuVideoView.h"

@class RNQinniuVideoView;

NS_ASSUME_NONNULL_BEGIN

@interface QNVideoConst : NSObject
@property (nonatomic, copy) NSString *appid;

@property (nonatomic, assign) NSInteger localUid;

@property (strong, nonatomic) QNRTCEngine *rtcEngine;
@property (strong, nonatomic) RNQinniuVideoView *qinniuVideoView;

@property(strong, nonatomic) NSDictionary* viewDict;

+ (instancetype)share;  // 共享引擎实例

-(void) setQInView:(RNQinniuVideoView *) videoView;
@end

NS_ASSUME_NONNULL_END
