package com.example.cinapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyLocationActivity extends Activity implements LocationListener {
    private Permissions locationPermission;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);
        locationPermission = new Permissions(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (locationPermission.havePermission()) {
            takeLocation();
        }else{
            locationPermission.setPermission();
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        updateLocationText(location);
    }

    @SuppressLint("SetTextI18n")
    private void updateLocationText(Location location) {
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText("Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }

    @SuppressLint("MissingPermission")
    void takeLocation(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null){
            updateLocationText(location);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(!locationPermission.havePermission()){
            Log.i("Permission: ", "Permission has been denied by user");
            locationPermission.errorPermission();
        }else{
            takeLocation();
        }
    }
}
