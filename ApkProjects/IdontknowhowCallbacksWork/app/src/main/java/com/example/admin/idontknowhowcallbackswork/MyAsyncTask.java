package com.example.admin.idontknowhowcallbackswork;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.concurrent.Executor;


/**
 * Created by Admin on 13.09.2018.
 */

public abstract class MyAsyncTask {

    private static final int STATUS_POST = 1;

    private static InternalHandler sHandler = null;

    private Handler mHandler = null;

    private MyExectuor myExectuor = new MyExectuor();


    public MyAsyncTask(){

        mHandler = new Handler(Looper.getMainLooper());
    }

    public MyAsyncTask(@Nullable Looper looper) {

        mHandler = looper == null || looper == Looper.getMainLooper() ?
                getMainHandler() : new Handler(looper);

    }


    private Thread newThread(Runnable r) {

        return new Thread(r);
    }



    public void execute() {

        myExectuor.execute(new Runnable() {
            @Override
            public void run() {

                String result = doInBackground();

                postResult(result);
            }
        });
    }


    private Handler getHandler() {


        return mHandler;
    }

    private Handler getMainHandler() {

        if (sHandler == null) {

            sHandler = new InternalHandler(Looper.getMainLooper());
        }

        return sHandler;
    }


    private void postResult(String result){

        Message msg = getHandler().obtainMessage(STATUS_POST, new MyAsyncTaskResult(this, result));
        msg.sendToTarget();

    }


    abstract String doInBackground();


    private void onPostExecute(String result) {

    }


    private static class MyAsyncTaskResult {

        MyAsyncTask mTask;
        String result;

        private MyAsyncTaskResult(MyAsyncTask mTask, String result) {

            this.mTask = mTask;
            this.result = result;
        }
    }


    private static class MyExectuor implements Executor {

        Runnable mActive;

        @Override
        public void execute(@NonNull Runnable command) {

            mActive = command;
            new Thread(command).start();
        }
    }


    private static class InternalHandler extends Handler {


        InternalHandler(Looper looper) {

            super(looper);
        }


        @Override
        public void handleMessage(Message msg) {

            MyAsyncTaskResult taskResult;

            switch (msg.what) {

                case STATUS_POST:
                    taskResult = (MyAsyncTaskResult) msg.obj;
                    taskResult.mTask.onPostExecute(taskResult.result);

            }


        }
    }
}
