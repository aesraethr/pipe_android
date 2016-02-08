# PIPE ANDROID

This android lib, as [pipe.js](https://git.ebu.io/pipe/pipe-js) for web app, collects data from an **Android app** for the pipe-collect project.
Before starting, you need to have a **site key** for collecting data related to your application. If you don't have one, [Michael Barroco](barroco@ebu.ch) could help you.

## 1. Getting the Pipe Collect lib

### First requirements

* Check that you have correct **permissions** about this repository.
* Install **[Android studio](http://developer.android.com/sdk/index.html)**
* You are **Git** ready

### Setting up

* Open your Android studio : 
* Go to : File -> New -> **Project from version control** -> git
* Git repository url : git@git.ebu.io:pipe/pipe_android.git (if you got problems, you can **clone** in https)
* If window **gradle** project appears, click on "import Gradle project" link. Then default options and *OK*
* At the bottom right (Git config), **checkout the develop branch** (Ok for naming local branch as 'develop')
* Now, you should have all files of the lib in the left explorer panel. (You should have files in *app > java > ch.ebu.pipe_android*)


## 2. Using lib in your Android app

### Importing lib in your app

* Go to File -> New -> Import module 
* Choose Pipe collect lib directory you cloned in the previous step 
* Check Import checkBox
* Module name : PipeCollect

### Configuring your app

1. Your main Android app, which includes pipe library must contains **INTERNET permission** in your AndroidManifest.xml file.
```xml
<uses-permission android:name="android.permission.INTERNET" />
```
_____________________

2. You need to **extend** your application class with the PipeCollect class
```java
public class TestApplication extends PipeCollect {

        //Your app code
}
```
_____________________

3. You have to set a string variable in your app with your **site key** (res/values/strings.xml)
```xml
    <resources>
        <string name="app_name">My Application</string>
        <string name="action_settings">Settings</string>
        <string name="site_key">yourSiteKey12345</string>
    </resources>
```
_____________________

4. Don't forget adding the line about **your application** class (here .TestApplication) in your manifest file
```xml
    <application
        android:name=".TestApplication"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
    </application>
```

### Collecting data

1. You, first have to **set the configuration** with your site key : 
```java
    public class TestApplication extends PipeCollect {

        private static TestApplication instance;
    
        @Override
        public void onCreate() {
            super.onCreate();
            instance = this;
            setConfiguration(getString(R.string.site_key), "your.domain.name");
        }
    
        public static TestApplication getInstance(){
            return instance;
        }
    
    }
```
_____________________

2. Example of collecting data in main activity on button click event
```java
    button.setOnClickListener(new View.OnClickListener() {
    
        public void onClick(View v) {
    
            tv.setText(sw.getRandomSentence());
            Data data = new Data();
            data.setId(123);
            data.setContent("mon beau contenu");
            data.setName("Damien");
    
    
            TestApplication.getInstance().collectData("data_test_android", data);
        }
    });
```