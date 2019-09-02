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

- (void)setShowLocalVideo:(BOOL)showLocalVideo {
    NSLog(@"--setShowLocalVideo---%@", self);
    if (showLocalVideo) {
        UIView *preView = _rtcEngine.previewView;
        RNQinniuVideoView *qnView = [QNVideoConst share].qinniuVideoView;
//        CGRect qnRect = qnView.frame;
        preView.bounds = CGRectMake(0, 0, 300, 300);
        preView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
        [qnView insertSubview:preView atIndex:0];
//        NSLayoutConstraint *purpleViewTopConstraint = [NSLayoutConstraint constraintWithItem:preView attribute:NSLayoutAttributeTop relatedBy:NSLayoutRelationEqual toItem:qnView attribute:NSLayoutAttributeTop multiplier:1.0 constant:0];
//
//        NSLayoutConstraint *purpleViewTopConstraintBottom = [NSLayoutConstraint constraintWithItem:preView attribute:NSLayoutAttributeBottom relatedBy:NSLayoutRelationEqual toItem:qnView attribute:NSLayoutAttributeBottom multiplier:1.0 constant:0];
//
//
//        NSLayoutConstraint *purpleViewTopConstraintLeft = [NSLayoutConstraint constraintWithItem:preView attribute:NSLayoutAttributeLeft relatedBy:NSLayoutRelationEqual toItem:qnView attribute:NSLayoutAttributeLeft multiplier:1.0 constant:0];
//
//        NSLayoutConstraint *purpleViewTopConstraintRight = [NSLayoutConstraint constraintWithItem:preView attribute:NSLayoutAttributeRight relatedBy:NSLayoutRelationEqual toItem:qnView attribute:NSLayoutAttributeRight multiplier:1.0 constant:0];
//        [qnView addConstraint:purpleViewTopConstraint];
//        [qnView addConstraint:purpleViewTopConstraintBottom];
//        [qnView addConstraint:purpleViewTopConstraintLeft];
//        [qnView addConstraint:purpleViewTopConstraintRight];
        
        [_rtcEngine startCapture];
    }
}

-(void)setRemoteUid:(NSInteger)remoteUid {

    NSLog(@"--setRemoteUid---%@", self);
    if (remoteUid > 0) {
//       self.render = [[QNVideoRender alloc] init];
        videoView = [[QNVideoView alloc] init];
         videoView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
          [self addSubview: videoView];
//        videoView.fillMode = QNVideoFillModeStretch;
//        [self addSubview:videoView];
//        self.render.renderView = videoView;
    }
}

-(QNVideoView *)RemoteView {
    return videoView;
}


//+ (instancetype)shareView {
//    static dispatch_once_t onceToken;
//    dispatch_once(&onceToken, ^{
//        perView = [self new];
//    });
//    return perView;
//}


@end
