package com.example.admin.idontknowhowcallbackswork;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by Admin on 14.09.2018.
 */

public class MyExecutor implements Executor {

    private WorkerThread handlerThread;

    public MyExecutor() {

        handlerThread = new WorkerThread("Fred");
        handlerThread.start();
        handlerThread.prepareHandler();
    }


    @Override
    public void execute(@NonNull Runnable command) {

    }

    public void executeInBackground(@NonNull Runnable runnable) {

        handlerThread.postTask(runnable);
    }
}
