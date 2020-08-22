package com.example.fileexplorer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.example.simplefileexplorer.SimpleFileExplorerActivity;

public class MainActivity extends AppCompatActivity {

    private final String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private final int file_explorer_request_result_code = 22;
    private final int permissions_request_code = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{this.permission},
                    this.permissions_request_code);
        }
        else {
            this.startFileExplorerLibrary();
        }
    }

    private void startFileExplorerLibrary(){
        Intent intent = new Intent(this, SimpleFileExplorerActivity.class);
        startActivityForResult(intent, file_explorer_request_result_code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == this.permissions_request_code){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                this.startFileExplorerLibrary();
            }
            else{
                Toast.makeText(this, "Permission not granted.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data != null){
            String selectedAbsolutePath = data.getStringExtra(SimpleFileExplorerActivity.ON_ACTIVITY_RESULT_KEY);
            Toast.makeText(this, selectedAbsolutePath, Toast.LENGTH_SHORT).show();
        }
    }
}
