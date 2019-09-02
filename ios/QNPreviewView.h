//
//  QNPreviewView.h
//  RNQiniu
//
//  Created by 亚米 on 2019/8/29.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "QNVideoConst.h"
NS_ASSUME_NONNULL_BEGIN

@interface QNPreviewView : UIView
@property (nonatomic) BOOL showLocalVideo;
@property (strong, nonatomic) QNRTCEngine *rtcEngine;
@end

NS_ASSUME_NONNULL_END
