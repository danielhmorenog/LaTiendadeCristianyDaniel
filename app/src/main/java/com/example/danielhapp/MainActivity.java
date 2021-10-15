package com.example.danielhapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.danielhapp.service.webService;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    Button btn_salir;
    Intent internet;

     private GoogleApiClient googleApiClient;
     private FirebaseAuth mAuth;
     private GoogleSignInClient cli;
     ImageView imgerror;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//renderiza el xml de android

       btn_salir = findViewById(R.id.btnSalirR);
       mAuth = FirebaseAuth.getInstance();

       btn_salir.setOnClickListener(view -> {

           mAuth.signOut();
           logOut();
           startActivity(new Intent(MainActivity.this, LoginActivity.class));
       });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();


        imgerror = findViewById(R.id.imagen_error);
        imgerror.setVisibility(View.INVISIBLE);

        internet = new Intent(this,webService.class);

    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user= mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    public void goLogInScream(){
       Intent intent = new Intent(this, LoginActivity.class);
       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(intent);
    }

    public void logOut(){
        mAuth.signOut();
    Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
        @Override
        public void onResult(@NonNull Status status) {
            if(status.isSuccess()){
                goLogInScream();
            }else{
                Toast.makeText(MainActivity.this,"",Toast.LENGTH_SHORT).show();
            }
        }
    });
    }

    public void ConexionInternet(){
       // Intent r = new Intent(this, )


        startActivity(internet);

    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}