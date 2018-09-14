package com.example.admin.idontknowhowcallbackswork;

import android.graphics.Bitmap;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.net.MalformedURLException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Admin on 13.09.2018.
 */

public class DownloadTask implements Runnable {

    private static final String TAG = "DownloadTask";

    private Rückruf callback;

    public DownloadTask(Rückruf rückruf){

        this.callback = rückruf;
    }

    @Override
    public void run() {

        Looper.prepare();

        Bitmap downloadedPicture;
        try {

           downloadedPicture = Connection.downloadPicture("http://hintergrundbild.org/wallpaper/full/6/0/3/39009-hochaufloesende-hintergrundbilder-1920x1080-fuer-samsung-galaxy-s7.jpg");

           if(downloadedPicture != null){

               callback.onPostExecute(downloadedPicture);
           } else {
               callback.onFailure();
           }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Looper.loop();
    }



    interface Rückruf {

        void onPostExecute(Bitmap result);

        void onFailure();

    }
}
