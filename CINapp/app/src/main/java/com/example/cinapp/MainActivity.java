package com.example.cinapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Permissions writeExternalStoragePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyLog.debug("devlog", "on Create - MainActivity");

        writeExternalStoragePermission = new Permissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        ((Button) findViewById(R.id.button2)).setOnClickListener(v -> {
            Log.d("devlog", "on click on button 2");
            Intent intent = new Intent(this, MyActivity.class);
            startActivity(intent);
        });

        ((Button) findViewById(R.id.button)).setOnClickListener(v -> {
            Log.d("devlog", "on click on button 1");
            Intent intent = new Intent(this, MyService1.class);
            intent.putExtra("TEXTO", "Resolvido");
            startService(intent);
        });

        ((Button) findViewById(R.id.button3)).setOnClickListener(v -> {
            if(writeExternalStoragePermission.havePermission()){
                Log.d("devlog", "on click on button 3");
                Intent intent = new Intent(this, MyService2.class);
                intent.putExtra("TEXTO", "Hello Wolrd!");
                startService(intent);
            }else{
                writeExternalStoragePermission.setPermission();
            }

        });

        ((Button) findViewById(R.id.button4)).setOnClickListener(v -> {
            Log.d("devlog", "on click on button 4");
            Intent intent = new Intent(this, MyLocationActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(!writeExternalStoragePermission.havePermission()){
            Log.i("Permission: ", "Permission has been denied by user");
            writeExternalStoragePermission.errorPermission();
        }
    }
}