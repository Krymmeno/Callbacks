package com.example.admin.idontknowhowcallbackswork;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

//https://github.com/googlesamples/android-MediaBrowserService/
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView textView;
    Handler mUiHandler = new Handler();
    WorkerThread workerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == 2) {

                        Log.d(TAG, "run: "+i);

                        mUiHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,
                                        "I am at the middle of background task",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
                    }
                }
                mUiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,
                                "Background task is completed",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
        };

        workerThread = new WorkerThread("Fred");

        Log.d(TAG, "onCreate: "+workerThread.getLooper());

        workerThread.start();
        workerThread.prepareHandler();
        workerThread.postTask(task);
        workerThread.postTask(task);

    }

    @Override
    protected void onDestroy() {
        workerThread.quit();
        super.onDestroy();
    }
}
