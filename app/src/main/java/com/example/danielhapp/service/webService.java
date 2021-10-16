package com.example.danielhapp.service;


import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import com.example.danielhapp.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class webService extends Service {

    public webService(){

    }

    FirebaseAuth mAuth;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public void onCreate() {
        super.onCreate();
    mAuth = FirebaseAuth.getInstance();

    internet inte = new internet();
    inte.execute();

    }

    public class internet extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            conexion();
            return null;
        }



    }



   public void conexion() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            Log.d("", "Conexion establecida");

        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
