package com.example.cinapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    boolean storage = false;
    boolean location = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyLog.debug("devlog", "on Create - MainActivity");

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
            if(storage){
                Log.d("devlog", "on click on button 3");
                Intent intent = new Intent(this, MyService2.class);
                intent.putExtra("TEXTO", "Hello Wolrd!");
                startService(intent);
            }else{
                Toast.makeText(this, "Você não permitiu acesso a memoria", Toast.LENGTH_SHORT).show();
            }

        });

        ((Button) findViewById(R.id.button4)).setOnClickListener(v -> {
            if(location){
                Log.d("devlog", "on click on button 4");
                Intent intent = new Intent(this, MyLocationActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Você não permitiu acesso a localização", Toast.LENGTH_SHORT).show();
            }

        });

        initPermissions();
    }

    private void initPermissions(){
        if(!getPermission()){
            setPermission();
            storage = checkPermissionStorage();
            location = checkPermissionLocation();
        }else{
            storage = true;
            location = true;
        }
    }

    private boolean getPermission(){
        return (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED);
    }

    private boolean checkPermissionLocation(){
        return (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED);
    }

    private boolean checkPermissionStorage(){
        return (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED);
    }

    private void setPermission(){
        String[] permissionsList = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION};

        ActivityCompat.requestPermissions(this, permissionsList, 1);
    }

    private void errorPermission(){
        Toast.makeText(this, "Para tudo funcionar dê acesso a localização e memoria", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED){
            Log.i("Permission: ", "Permission has been denied by user");
            errorPermission();
        }
    }
}