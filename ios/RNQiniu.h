//
//  RNQiniu.h
//  RNQiniu
//
//  Created by 吕少立 on 2019/8/28.
//  Copyright © 2019年 yami. All rights reserved.
//
#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#else
#import <React/RCTBridgeModule.h>
#endif
#import <QNRTCKit/QNRTCKit.h>

static NSString *cameraTag = @"camera";
static NSString *screenTag = @"screen";
@interface RNQiniu : NSObject <RCTBridgeModule, QNRTCEngineDelegate>
@property (nonatomic, assign) NSInteger bitrate;
@end
  
