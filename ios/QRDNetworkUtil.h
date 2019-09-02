//
//  QRDNetworkUtil.h
//  RNQiniu
//
//  Created by 亚米 on 2019/8/28.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface QRDNetworkUtil : NSObject

+ (void)requestTokenWithRoomName:(NSString *)roomName
                           appId:(NSString *)appId
                          userId:(NSString *)userId
               completionHandler:(void (^)(NSError *error, NSString *token))completionHandler;

+ (void)requestRoomUserListWithRoomName:(NSString *)roomName
                                  appId:(NSString *)appId
                      completionHandler:(void (^)(NSError *error, NSDictionary *userListDic))completionHandler;

@end
