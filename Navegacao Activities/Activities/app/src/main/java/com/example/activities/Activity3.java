package com.example.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.activities.databinding.Activity3Binding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class Activity3 extends AppCompatActivity {
    private Activity3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("TELA", "Tela 3");
        startService(intent);

        binding.buttonBackActivity2.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Você tem certeza?")
                    .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        });

        binding.buttonGoToActivity4.setOnClickListener(view -> {
            Snackbar.make(this, view, "Não há tela 4", Snackbar.LENGTH_LONG).show();
        });

        List<MyContact> contacts = ContactsHelper.getContacts(this);
        if(contacts.size() >= 3){
            MyContact contact = contacts.get(2);
            Log.d("JMN", "ID: " + contact.getId() + " Name: " + contact.getName());
        }
    }
}