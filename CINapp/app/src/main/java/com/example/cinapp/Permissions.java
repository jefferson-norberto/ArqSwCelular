package com.example.cinapp;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class Permissions {
    private Activity activity;
    private String permission;

    public Permissions(Activity activity, String permission){
        this.activity = activity;
        this.permission = permission;
    }

    boolean havePermission() {
        return (ActivityCompat.checkSelfPermission(activity.getApplicationContext(),
                permission) == PackageManager.PERMISSION_GRANTED);
    }

    void setPermission(){
        String[] permissionsList = {
                permission};
        ActivityCompat.requestPermissions(activity, permissionsList, 1);
    }

    public void errorPermission(){
        Toast.makeText(activity, "Permissão " + permission + " não concedida", Toast.LENGTH_LONG).show();
    }

}
