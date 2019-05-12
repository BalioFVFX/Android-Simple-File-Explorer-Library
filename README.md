# Android-Simple-File-Explorer-Library
Simple, Responsive &amp; Easy To Use Library For Android

[![](https://jitpack.io/v/BalioFVFX/Android-Simple-File-Explorer-Library.svg)](https://jitpack.io/#BalioFVFX/Android-Simple-File-Explorer-Library)
# INTRODUCTION
### Showing file explorer in Android has never been easier.
<br />

# GETTING STARTED

#### GRADLE SETUP:
##### Step 1: Add it in your root build.gradle (Project) at the end of repositories

<br />

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
##### Step 2: Add it in your build.gradle (Module:app)

<br/>

```
	dependencies {
	        implementation 'com.github.BalioFVFX:Simple-Android-File-Explorer-Library:1.0.1'
	}
```

# USAGE

##### Note: Before showing the file explorer you must ask for "WRITE_EXTERNAL_STORAGE" permission.
![Permission Image](https://i.imgur.com/zxt34Vy.png)
<br/>
#### Showing the file explorer
```
Intent intent = new Intent(CONTEXT, SimpleFileExplorerActivity.
startActivityForResult(intent, REQUEST_CODE);
```
#### Getting the selected file absolute path
##### Note: Your activity must override the onActivityResult(); method.

```
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data != null){
            String selectedAbsolutePath = data.getStringExtra(SimpleFileExplorerActivity.ON_ACTIVITY_RESULT_KEY);
            Toast.makeText(CONTEXT, selectedAbsolutePath, Toast.LENGTH_SHORT).show();    
        }
    }
```

<p float="left">
  <img src="https://i.imgur.com/uX3iFqn.png" width="300" />
  <img src="https://i.imgur.com/WlVDRIG.png" width="300" /> 
</p>

#### Changing the default icons with your custom drawables

```
SimpleFileResources.imageFileId = R.drawable.ic_my_custom_file;
SimpleFileResources.imageDirectoryId = R.drawable.ic_my_custom_directory;
```

##### If you want to use the default icons:

```
SimpleFileResources.imageFileId = null;
SimpleFileResources.imageDirectoryId = null;
```

#### END
##### You are free to use this library however you want. Credit is appreciated but it's not requiered.
##### Feel free to suggest new features or report problems by opening new issue or contacting me by email.

[![](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=HU65XMSW3YZ5S)
