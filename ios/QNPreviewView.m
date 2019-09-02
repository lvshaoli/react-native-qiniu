//
//  QNPreviewView.m
//  RNQiniu
//
//  Created by 亚米 on 2019/8/29.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "QNPreviewView.h"

@implementation QNPreviewView
- (instancetype)init{
    
    if (self == [super init]) {
        _rtcEngine = [QNVideoConst share].rtcEngine;
    }
    
    return self;
}

- (void)setShowLocalVideo:(BOOL)showLocalVideo {
    NSLog(@"--setShowLocalVideo---%@", self);
//    UIView *contentView = [[UIView alloc] init];
//     UIView *preView = _rtcEngine.previewView;
//    preView.bounds = CGRectMake(0, 0, 100, 100);
    
//    UIView *preView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 100, 100)];
//    [preView setBackgroundColor:[UIColor greenColor]];
//    preView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
//    [self insertSubview:preView atIndex:0];
//    [contentView addSubview:preView];
    [self addSubview:_rtcEngine.previewView];
    [_rtcEngine startCapture];
}
@end
