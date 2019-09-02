/**
 * @Author: lvshaoli
 * @DATE: 2019-08-26
 * @TIME: 11:40
 * @DESC: ''
 * */


import React, {Component} from 'react';
import {Text, requireNativeComponent, Platform} from 'react-native';


let VideoViewManager;

if (Platform.OS === 'ios') {
  VideoViewManager = requireNativeComponent('RNQinNiuVideoView');
} else {
    VideoViewManager = requireNativeComponent('VideoViewManager');
}

export default VideoViewManager;

