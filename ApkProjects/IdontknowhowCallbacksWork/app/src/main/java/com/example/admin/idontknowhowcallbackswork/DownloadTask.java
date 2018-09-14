package com.example.admin.idontknowhowcallbackswork;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.net.MalformedURLException;

/**
 * Created by Admin on 13.09.2018.
 */

public class DownloadTask implements Runnable {

    private static final String TAG = "DownloadTask";

    private Download_Callback callback;

    public DownloadTask(Download_Callback downloadCallback) {

        this.callback = downloadCallback;
    }

    @Override
    public void run() {

        Bitmap downloadedPicture;
        try {
            Log.d(TAG, "Thread mit dem Namen " + Thread.currentThread().getName());

            downloadedPicture = Connection.downloadPicture("http://hintergrundbild.org/wallpaper/full/8/2/b/35334-tuerkis-hintergrundbilder-2048x1152-iphone.jpg");

           if(downloadedPicture != null){

               postResult(downloadedPicture);
           } else {
               callback.onFailure();
           }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    private void postResult(final Bitmap result){

        Handler mainHandler = new Handler(Looper.getMainLooper());

        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onPostExecute(result);
            }
        });

    }


    interface Download_Callback {

        void onPostExecute(Bitmap result);

        void onFailure();

    }
}
