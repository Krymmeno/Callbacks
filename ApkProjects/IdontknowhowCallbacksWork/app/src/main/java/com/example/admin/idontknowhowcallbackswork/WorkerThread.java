package com.example.admin.idontknowhowcallbackswork;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

/**
 * Created by Admin on 14.09.2018.
 */

public class WorkerThread extends HandlerThread{

    private static final String TAG = "WorkerThread";
    
    private Handler backGroundHandler;

    public WorkerThread(String name) {
        super(name);
    }


    public void prepareHandler(){

        try {

            backGroundHandler = new Handler(getLooper());
        } catch (NullPointerException ex) {

            Log.e(TAG, "getLooper() ist null ", null);
        }
    }


    //Reihe  die Aufgabe in die Queue ein,
    //da HandlerThread wird durch die Queue automatisch abgearbeitet
    public void postTask(Runnable r){

        backGroundHandler.post(r);
    }


}
