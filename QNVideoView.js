/**
 * @Author: lvshaoli
 * @DATE: 2019-08-28
 * @TIME: 14:50
 * @DESC: ''
 * */

import  React, {Component} from 'react'
import {PropTypes} from 'prop-types'
import {
    requireNativeComponent,
    View,
    Platform
} from 'react-native'

let VideoViewManager;

if (Platform.OS === 'ios') {
    VideoViewManager = requireNativeComponent('RNQinNiuVideoView');
} else {
    VideoViewManager = requireNativeComponent('VideoViewManager');
}


export default class QNVideoView extends Component {

    render() {
        return (
            <VideoViewManager {...this.props}/>
        )
    }
}

QNVideoView.propTypes = {
    showLocalVideo: PropTypes.bool,
    remoteUid: PropTypes.number,
    zOrderMediaOverlay: PropTypes.bool,
    ...View.propTypes
};


