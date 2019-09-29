//
//  QNPreviewViewManager.m
//  RNQiniu
//
//  Created by lvshaoli on 2019/8/29.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "QNPreviewViewManager.h"
//#import "QNPreviewView.h"
#import "QNVideoConst.h"

@implementation QNPreviewViewManager
RCT_EXPORT_MODULE()
//RCT_EXPORT_VIEW_PROPERTY(showLocalVideo, BOOL)

- (UIView *)view {
     QNRTCEngine * _rtcEngine = [QNVideoConst share].rtcEngine;
    UIView *preView = _rtcEngine.previewView;
    return preView;
}

@end
