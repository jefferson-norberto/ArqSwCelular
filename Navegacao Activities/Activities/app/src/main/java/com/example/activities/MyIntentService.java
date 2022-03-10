package com.example.activities;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String name;

            ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);

            if(activityManager != null){
                List<ActivityManager.AppTask> tasks = activityManager.getAppTasks();
                name = tasks.get(0).getTaskInfo().topActivity.getClassName();
            } else{
                name = "";
            }

            Toast.makeText(this, "Nome da Tela: "
                    + name, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("JMN", "Service on destroy");
    }
}