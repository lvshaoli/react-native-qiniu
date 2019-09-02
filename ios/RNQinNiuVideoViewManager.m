//
//  RNQinNiuVideoViewManager.m
//  RNQiniu
//
//  Created by lvshaoli on 2019/8/28.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "RNQinNiuVideoViewManager.h"
#import "RNQinniuVideoView.h"
#import "QNVideoConst.h"

@implementation RNQinNiuVideoViewManager
RCT_EXPORT_MODULE()

RCT_EXPORT_VIEW_PROPERTY(showLocalVideo, BOOL)
RCT_EXPORT_VIEW_PROPERTY(remoteUid, NSInteger)

- (UIView *)view {
    RNQinniuVideoView* qnView = [RNQinniuVideoView new];
    [[QNVideoConst share] setQInView:qnView];
    
//    UIView* qnView = [UIView new];
//
//    UIView *littleView = [UIView new];
////    littleView.bounds = CGRectMake(105, 105, 100, 100);
//    [littleView setBackgroundColor:[UIColor greenColor]];
//    littleView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
//    [qnView addSubview:littleView];
   
    
    return qnView;
}


@end
