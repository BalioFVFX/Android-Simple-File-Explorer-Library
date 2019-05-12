package com.example.simplefileexplorer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SimpleFileExplorerActivity extends AppCompatActivity implements ActivityListener{


    private String STACK_KEY = "FRAGMENT_STACK_KEY";
    public static String ON_ACTIVITY_RESULT_KEY = "ABSOLUTE_PATH_KEY";


    private Button selectButton;
    private String selectedAbsolutePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_file_explorer);
        checkPermission();
        this.initViews();
        this.setSelectButtonClickListener();
        SimpleFileExplorerFragment fragment = new SimpleFileExplorerFragment();
        fragment.setListeners(this);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, fragment).addToBackStack(this.STACK_KEY).commit();

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                selectButton.setEnabled(false);
            }
        });
    }

    private void initViews(){
        this.initButton();
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
        SimpleFileExplorerFragment fragment = new SimpleFileExplorerFragment();
        fragment.setListeners(this);
        fragment.setDirectory(absolutePath);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).addToBackStack(this.STACK_KEY).commit();
    }

    @Override
    public void onFileSelect(String absolutePath) {
        this.selectedAbsolutePath = absolutePath;
        this.selectButton.setEnabled(true);
    }

    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            throw new SecurityException(String.format("Permission %s is not granted.", Manifest.permission.WRITE_EXTERNAL_STORAGE));
        }
    }
}
