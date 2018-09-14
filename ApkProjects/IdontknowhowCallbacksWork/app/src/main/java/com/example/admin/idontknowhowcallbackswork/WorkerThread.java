package com.example.admin.idontknowhowcallbackswork;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

        backGroundHandler = new Handler(getLooper());
    }


    //Reihe  die Aufgabe in die Queue ein,
    //da HandlerThread wird durch die Queue automatisch abgearbeitet
    public void postTask(Runnable r){

        backGroundHandler.post(r);
    }


}
