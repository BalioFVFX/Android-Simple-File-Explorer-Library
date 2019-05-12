# Android-Simple-File-Explorer-Library
Simple, Responsive &amp; Easy To Use Library For Android

[![](https://jitpack.io/v/BalioFVFX/Android-Simple-File-Explorer-Library.svg)](https://jitpack.io/#BalioFVFX/Android-Simple-File-Explorer-Library)
# INTRODUCTION
### Showing file explorer in Android has never been easier.
<br />

# GETTING STARTED

#### GRADLE SETUP:

##### Add it in your root build.gradle (Project) at the end of repositories

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
	        implementation 'com.github.BalioFVFX:Simple-Android-File-Explorer-Library:1.0'
	}
```

# USAGE

##### Note: Before showing the file explorer you must ask for "WRITE_EXTERNAL_STORAGE" permission.

<br/>

#### Showing the file explorer
```
Intent intent = new Intent(CONTEXT, SimpleFileExplorerActivity.
startActivityForResult(intent, REQUEST_CODE);
```
<br/>

#### Getting the selected file absolute path
![Permission Image](https://i.imgur.com/zxt34Vy.png)
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

<br/>

<img src="https://i.imgur.com/2dU8LR0.png" alt="drawing" width="360"/>
<img src="https://i.imgur.com/WlVDRIG.png" alt="drawing" width="360"/>

#### Changing the default icons with your custom drawables

<br/>

```
SimpleFileResources.imageFileId = R.drawable.ic_my_custom_file;
SimpleFileResources.imageDirectoryId = R.drawable.ic_my_custom_directory;
```
##### If you want to use the default icons:

<br/>

```
SimpleFileResources.imageFileId = null;
SimpleFileResources.imageDirectoryId = null;
```

#### END

##### You are free to use this library however you want. Credit is appreciated but it's not requiered.

##### Feel free to suggest new features or report problems by opening new issue or contacting me by email.

<br/>

[![](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=HU65XMSW3YZ5S)
