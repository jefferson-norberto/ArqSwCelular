package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.activities.databinding.ActivityMainBinding;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyBroadcast receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonGoToActivity2.setOnClickListener(view -> {
            startActivity((new Intent(this, Activity2.class)));
        });

        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("TELA", "Tela Principal");
        startService(intent);

        List<MyContact> contacts = ContactsHelper.getContacts(this);

        if(contacts.size() >= 1){
            MyContact contact = contacts.get(0);
            Log.d("JMN", "ID: " + contact.getId() + " Name: " + contact.getName());
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

    class MyBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("JMN", "Status do wifi mudou");
        }
    }
}