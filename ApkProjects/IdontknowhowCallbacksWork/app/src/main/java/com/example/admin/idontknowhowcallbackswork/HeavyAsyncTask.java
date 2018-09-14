package com.example.admin.idontknowhowcallbackswork;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Admin on 12.09.2018.
 */

public class HeavyAsyncTask extends AsyncTask<Void,Void,String>{

    private static final String TAG = "HeavyAsyncTask";

    private Callback callback;
    private Exception thrownExce;

    public HeavyAsyncTask(Callback callback){

        this.callback = callback;
    }



    @Override
    protected String doInBackground(Void... voids) {


            try {
                for(int i = 1; i <= 10; i++) {

                    Thread.sleep(1000);
                    Log.d(TAG, "doInBackground: "+i);
                }
                return null;
            } catch (Exception e) {

                thrownExce = e;
            }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        if(callback != null){

            if(s != null){

                callback.onSuccess(s);
            } else {

                callback.onFailure(thrownExce);
            }
        }


    }

    interface Callback{

        void onSuccess(String result);

        void onFailure(Exception e);

    }
}
