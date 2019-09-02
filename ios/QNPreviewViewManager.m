//
//  QNPreviewViewManager.m
//  RNQiniu
//
//  Created by lvshaoli on 2019/8/29.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "QNPreviewViewManager.h"
#import "QNPreviewView.h"

@implementation QNPreviewViewManager
RCT_EXPORT_MODULE()
RCT_EXPORT_VIEW_PROPERTY(showLocalVideo, BOOL)

- (UIView *)view {
    return [QNPreviewView new];
}
@end
