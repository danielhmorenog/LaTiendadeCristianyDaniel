package com.example.danielhapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyService extends Service {

    FirebaseAuth mAuth;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onCreate() {
        super.onCreate();
        mAuth = FirebaseAuth.getInstance();
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        NetworkInfo nInfo = cm.getNetworkInfo(cm.getActiveNetwork());
        if (nInfo.getType() == ConnectivityManager.TYPE_WIFI || nInfo.getType() == ConnectivityManager.TYPE_MOBILE) {

        } else {
            mAuth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}