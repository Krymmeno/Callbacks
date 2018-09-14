package com.example.admin.idontknowhowcallbackswork;

import android.graphics.Bitmap;

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

            downloadedPicture = Connection.downloadPicture("http://hintergrundbild.org/wallpaper/full/8/2/b/35334-tuerkis-hintergrundbilder-2048x1152-iphone.jpg");

           if(downloadedPicture != null){

               callback.onPostExecute(downloadedPicture);
           } else {
               callback.onFailure();
           }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    interface Download_Callback {

        void onPostExecute(Bitmap result);

        void onFailure();

    }
}
