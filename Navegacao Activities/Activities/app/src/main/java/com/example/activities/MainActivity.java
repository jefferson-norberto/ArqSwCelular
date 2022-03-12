package com.example.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.activities.databinding.ActivityMainBinding;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyBroadcast receiver;
    private Permissions contactPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        contactPermission = new Permissions(this, Manifest.permission.READ_CONTACTS);

        binding.buttonGoToActivity2.setOnClickListener(view -> {
            startActivity((new Intent(this, Activity2.class)));
        });

        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("TELA", "Tela Principal");
        startService(intent);

        if(contactPermission.havePermission()){
            List<MyContact> contacts = ContactsHelper.getContacts(this);

            if(contacts.size() >= 1){
                MyContact contact = contacts.get(0);
                Log.d("JMN", "ID: " + contact.getId() + " Name: " + contact.getName());
            }
        }else{
            contactPermission.setPermission();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        receiver = new MyBroadcast();
        IntentFilter intentFilter = new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (receiver != null){
            unregisterReceiver(receiver);
            receiver = null;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(!contactPermission.havePermission()){
            Log.i("Permission: ", "Permission has been denied by user");
            contactPermission.errorPermission();
        }
    }

    class MyBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("JMN", "Status do wifi mudou");
        }
    }
}