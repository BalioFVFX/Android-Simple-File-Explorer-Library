# Android-Simple-File-Explorer-Library
Simple, Responsive &amp; Easy To Use Library For Android\
**Warning: This library uses external legacy storage! android:requestLegacyExternalStorage="true".** Please read more about it here: https://developer.android.com/training/data-storage/use-cases#opt-out-scoped-storage


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
	        implementation 'com.github.BalioFVFX:Android-Simple-File-Explorer-Library:2.0.7'
	}
```

# USAGE

##### Note: Before showing the file explorer you must ask for "WRITE_EXTERNAL_STORAGE" permission.
![Permission Image](https://i.imgur.com/zxt34Vy.png)
<br/>
#### Showing the file explorer
```
Intent intent = new Intent(CONTEXT, SimpleFileExplorerActivity);
startActivityForResult(intent, REQUEST_CODE);
```
#### Disabling the select button for directories
```
Intent intent = new Intent(CONTEXT, SimpleFileExplorerActivity);
intent.putExtra(SimpleFileExplorerActivity.ENABLE_DIRECTORY_SELECT_KEY, false);
startActivityForResult(intent, REQUEST_CODE);
```
##### Note: By default you can select directories, however you may want to disable this functionality.

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
#### Note: Always check if the data is null, otherwise exception may occur. <br/>For example if the user doesn't select a directory / file and closes the file explorer data will be null.
<p float="left">
  <img src="https://i.imgur.com/n229g4p.png" width="300" />
  <img src="https://i.imgur.com/L0qowxq.png" width="300" /> 
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
##### You are free to include this library in your application. Credit is appreciated but it's not requiered.
##### Feel free to suggest new features or report problems by opening new issue or contacting me by email.
