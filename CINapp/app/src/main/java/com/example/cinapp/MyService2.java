package com.example.cinapp;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.icu.util.LocaleData;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyService2 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String text = intent.getExtras().getString("TEXTO");
        try {
            @SuppressLint("SdCardPath")
            File file = new File("/sdcard/Documents/", "myFile.txt");
            writeTextData(file, text);
            Toast.makeText(this, "Done" + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("devlog", e.getMessage());
            Toast.makeText(this, "Outro app já criou o arquivo myFile.txt em /sdcard/Documents", Toast.LENGTH_LONG).show();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void writeTextData(File file, String data) throws Exception {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
        }finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }
}
