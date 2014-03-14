GlassEyeGestureDemo
===================

Demo app for Google Glass's eye gesture detector. This app is capable of detecting "Wink" gesture or "Double blink" gesture. Note that eye gesture only works on new version of Glass. If you are an early explorer and have not swapped yours with new one, it won't work.

<img src="http://thorikawa.github.io/GlassEyeGestureDemo/img/eyegesture1.png" width="480" />
<img src="http://thorikawa.github.io/GlassEyeGestureDemo/img/eyegesture2.png" width="480" />

### Download
[v1.0 apk file](https://github.com/thorikawa/GlassEyeGestureDemo/releases/download/v1.0/EyeGestureDemo-debug.apk)

You might need `adb` to install it.

### Source Code
This repo consists of two projects. [EyeGestureStub](EyeGestureStub) is a library project for stubbing Google Glass's core framework [EyeGestureDemoApp](EyeGestureDemoApp) is an actual app.

### How it works
EyeGesture and EyeGestureManager included in this project are just stubs, but once apk file is installed to Google Glass, it uses actual library included in Glass itself.

If you wanna use eye gesture in your own application, you'd better to use EyeGestureStub project as it is. If you want to include those classes in your project, please use EyeGesture and EyeGestureManager class from this project with the SAME package name (com.google.android.glass.eye) and class name.

### License
[Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

### Acknowledgements
Refered [winky](https://github.com/kaze0/winky) project to learn how to receive the result of eye gesture. Thank you for the great work! Of course, great thank you for Google Glass's team!
