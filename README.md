提示：
  由于时间关系，目前对七牛实时视频语音封装还不完善，只是简单的搭了个框架，只是简单实现了视频会话，后期会持续完善
  
# react-native-qiniu



## Getting started

`$ npm install react-native-qiniu --save`

### Mostly automatic installation

`$ react-native link react-native-qiniu`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-qiniu` and add `RNQiniu.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNQiniu.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.qiniulibrary.RNQiniuPackage;` to the imports at the top of the file
  - Add `new RNQiniuPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-qiniu'
  	project(':react-native-qiniu').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-qiniu/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-qiniu')
  	```


## Usage
```javascript
import RNQiniu from 'react-native-qiniu';

// TODO: What to do with the module?
RNQiniu;
```
  
