# Android-TextureVideoView
all scaleType available now

## Example

add code build.gradle in project<br />
``` groovy
allprojects {
    repositories {
        jcenter()
    }
}
```

add code build.gradle in module<br />
``` groovy
dependencies {
    compile 'com.crust87:texturevideoview:1.0.4'
}
```

append your layout xml<br />
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <com.crust87.texturevideoview.widget.TextureVideoView
        android:id="@+id/videoClip"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@android:color/transparent"
        app:scaleType="fitCenter" />
</RelativeLayout>
```

and set video Uri<br />
```java
mVideoView.setVideoURI(videoUri);
mVideoView.start();
```

## License
Copyright 2015 Mabi

Licensed under the Apache License, Version 2.0 (the "License");<br/>
you may not use this work except in compliance with the License.<br/>
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software<br/>
distributed under the License is distributed on an "AS IS" BASIS,<br/>
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.<br/>
See the License for the specific language governing permissions and<br/>
limitations under the License.