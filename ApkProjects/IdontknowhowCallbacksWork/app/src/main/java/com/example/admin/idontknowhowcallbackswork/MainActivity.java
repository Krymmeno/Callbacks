package com.example.admin.idontknowhowcallbackswork;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ScheduledThreadPoolExecutor;

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

                Log.d(TAG, "Thread mit dem Namen "+Thread.currentThread().getName());

                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                        Log.d(TAG, "run: "+i);

                }
                mUiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: finished");
                    }
                });
            }
        };

        SerialExecutor serialExecutor = new SerialExecutor();

        DownloadTask downloadTask = new DownloadTask(new DownloadTask.Download_Callback() {

            /**
             *
             * Runs on MainThread
             * @param result
             */

            @Override
            public void onPostExecute(final Bitmap result) {

                final ImageView imageView = findViewById(R.id.imageView);


                        imageView.setImageBitmap(result);
                }

            /**
             *
             * Still runs on BackgroundThread
             */
            @Override
            public void onFailure() {

            }
        });

        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(2);


        serialExecutor.executeInBackground(task);







    }

    @Override
    protected void onDestroy() {
        workerThread.quit();
        super.onDestroy();
    }
}
