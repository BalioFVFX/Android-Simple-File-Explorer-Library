package com.example.simplefileexplorer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class SimpleFileExplorerActivity extends AppCompatActivity implements ActivityListener{


    private String STACK_KEY = "FRAGMENT_STACK_KEY";
    public static String ENABLE_DIRECTORY_SELECT_KEY = "DIRECTORY_SELECT_KEY";
    public static String ON_ACTIVITY_RESULT_KEY = "ABSOLUTE_PATH_KEY";


    private Button selectButton;
    private ImageView fileTypeImageView;
    private String selectedAbsolutePath;
    private boolean isDirectorySelectEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_file_explorer);
        checkPermission();
        this.initViews();
        this.setSelectButtonClickListener();
        this.configureButtonFromUserPreferences();
        SimpleFileExplorerFragment fragment = new SimpleFileExplorerFragment();
        fragment.setListeners(this);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, fragment).commit();
        this.selectedAbsolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    private void initViews(){
        this.initButton();
        this.initImageView();
    }

    private void initImageView() {
        this.fileTypeImageView = this.findViewById(R.id.iv_file_type);
    }


    private void initButton(){
        this.selectButton = this.findViewById(R.id.btn_select);
    }

    private void setSelectButtonClickListener(){
        this.selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(ON_ACTIVITY_RESULT_KEY, selectedAbsolutePath);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    public void onDirectoryChanged(String absolutePath) {
        this.selectedAbsolutePath = absolutePath;
        this.updateButtonSelectStateOnDirectory();
        SimpleFileExplorerFragment fragment = new SimpleFileExplorerFragment();
        fragment.setListeners(this);
        fragment.setDirectory(absolutePath);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).addToBackStack(STACK_KEY).commit();
    }

    @Override
    public void onFileSelect(FileModel fileModel) {
        if(fileModel.isSelected()){
            this.selectedAbsolutePath = fileModel.getAbsolutePath();
            this.fileTypeImageView.setImageResource(R.drawable.ic_file);
            this.selectButton.setEnabled(true);
        }
        else{
            this.selectedAbsolutePath = fileModel.getDirectoryPath();
            this.fileTypeImageView.setImageResource(R.drawable.ic_folder);
            this.updateButtonSelectStateOnDirectory();
        }

    }

    @Override
    public void onBackButtonPressed(String absolutePath) {
        this.fileTypeImageView.setImageResource(R.drawable.ic_folder);
        this.selectedAbsolutePath = absolutePath;
    }

    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            throw new SecurityException(String.format("Permission %s is not granted.", Manifest.permission.WRITE_EXTERNAL_STORAGE));
        }
    }

    private void configureButtonFromUserPreferences(){
        Intent intent = getIntent();
        if(intent.hasExtra(ENABLE_DIRECTORY_SELECT_KEY)){
            this.isDirectorySelectEnabled = intent.getBooleanExtra(ENABLE_DIRECTORY_SELECT_KEY, true);
        }
        else{
            this.isDirectorySelectEnabled = true;
        }
        this.selectButton.setEnabled(this.isDirectorySelectEnabled);
    }

    private void updateButtonSelectStateOnDirectory(){
        if(this.isDirectorySelectEnabled){
            this.selectButton.setEnabled(true);
        }
        else{
            this.selectButton.setEnabled(false);
        }
    }
}
