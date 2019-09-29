//
//  RNQinniuVideoView.m
//  RNQiniu
//
//  Created by 亚米 on 2019/8/28.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "RNQinniuVideoView.h"

@implementation RNQinniuVideoView
static RNQinniuVideoView *perView;
QNVideoView *videoView;

- (instancetype)init{
    
    if (self == [super init]) {
        _rtcEngine = [QNVideoConst share].rtcEngine;
    }
    
    return self;
}

-(void)setRemoteUid:(NSInteger)remoteUid {
    if (remoteUid > 0) {
        videoView = [[QNVideoView alloc] init];
         videoView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
          [self addSubview: videoView];
    }
}

-(QNVideoView *)RemoteView {
    return videoView;
}

@end
