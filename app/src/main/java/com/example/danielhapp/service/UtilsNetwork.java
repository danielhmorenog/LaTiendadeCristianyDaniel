package com.example.danielhapp.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.danielhapp.LoginActivity;
import com.example.danielhapp.MainActivity;

public class UtilsNetwork {
    public static boolean isOnline(Context context) {

        ConnectivityManager connectivityManager =(ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager!= null ){

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            Log.d("", "Conexion establecida");
            if(networkInfo != null){
                return networkInfo.isConnected();
            }
        }
        return false;
    }
}
