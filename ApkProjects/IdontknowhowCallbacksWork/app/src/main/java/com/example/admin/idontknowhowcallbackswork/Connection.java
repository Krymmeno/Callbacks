package com.example.admin.idontknowhowcallbackswork;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Admin on 13.09.2018.
 */

public class Connection {

    private static final String TAG = "Connection";

    public static boolean isNetworkAvailable(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        boolean isAvailable = info != null && info.isConnectedOrConnecting();

        return isAvailable;
    }


    public static Bitmap downloadPicture(String strURL) throws MalformedURLException{

        Bitmap bitmap;

        URL url = new URL(strURL);
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) url.openConnection();


            //connection.setConnectTimeout(5000);
            //connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();

            if(status == HttpURLConnection.HTTP_OK){

                InputStream inputStream = connection.getInputStream();

                bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;

            } else {
                Log.e(TAG, "downloadPicture: " + status, null);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect();

        }

        return null;

    }

}
