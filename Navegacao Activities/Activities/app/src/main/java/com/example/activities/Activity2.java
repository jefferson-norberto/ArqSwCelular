package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;

import com.example.activities.databinding.Activity2Binding;
import com.example.activities.databinding.ActivityMainBinding;

import java.util.List;

public class Activity2 extends AppCompatActivity {
    private Activity2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonGoToActivity3.setOnClickListener(view -> {
            startActivity(new Intent(this, Activity3.class));
        });

        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("TELA", "Tela 2");
        startService(intent);

        List<MyContact> contacts = ContactsHelper.getContacts(this);

        if(contacts.size() >= 2){
            MyContact contact = contacts.get(1);
            Log.d("JMN", "ID: " + contact.getId() + " Name: " + contact.getName());
        }
    }
}