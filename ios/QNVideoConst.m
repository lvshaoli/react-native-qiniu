//
//  QNVideoConst.m
//  RNQiniu
//
//  Created by lvshaoli on 2019/8/28.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "QNVideoConst.h"


@implementation QNVideoConst


static QNVideoConst *_person;
+ (instancetype)allocWithZone:(struct _NSZone *)zone{
    static dispatch_once_t predicate;
    dispatch_once(&predicate, ^{
        _person = [super allocWithZone:zone];
    });
    return _person;
}

+ (instancetype)share {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _person = [[self alloc]init];
    });
    return _person;
}

-(void) setQInView:(RNQinniuVideoView *) videoView{
    if (videoView) {
        self.qinniuVideoView = videoView;
    } else {
        self.qinniuVideoView = [RNQinniuVideoView new];
    }
}


- (id)copyWithZone:(NSZone *)zone {
    return _person;
}


@end
