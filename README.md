
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
  