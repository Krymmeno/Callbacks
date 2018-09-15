package com.example.admin.idontknowhowcallbackswork;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;


/**
 * Created by Admin on 15.09.2018.
 */

public class DownloadService extends IntentService {

    private static final String TAG = "DownloadService";

    public DownloadService() {
        super("Download-Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d(TAG, "Start-Service");
        try {
            for(int i = 0; i < 10; i++){

                Thread.sleep(1000);
                Log.d(TAG, "onHandleIntent: "+i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishResults("Result");
    }


    public void publishResults(String result){

        Intent resultIntent = new Intent("com.example.admin.idontknowhowcallbackswork");
        resultIntent.putExtra("Result", result);
        sendBroadcast(resultIntent);
    }


}
