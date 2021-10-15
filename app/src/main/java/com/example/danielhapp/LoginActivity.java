package com.example.danielhapp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{


    TextInputEditText Logemail,Logpass;
    TextView RegisterHere;
    Button btn_Login;
    private FirebaseAuth mAuth;

    Intent servicio;

    //con google

    private GoogleApiClient googleApiClient;
    private GoogleSignInClient mGoogleCli;
    private final static int RC_SING_IN = 777;
    private SignInButton signInButton;
    private FirebaseAuth.AuthStateListener firebaseA;

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Logemail = findViewById(R.id.etLoginEmail);
        Logpass = findViewById(R.id.etLoginPass);
        RegisterHere = findViewById(R.id.tvRegisterHere);
        btn_Login = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();


        RegisterHere.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        // inicio de seccion con google

        firebaseA = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if(user !=null){
                    goMainScreen();
                }
            }
        } ;

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        signInButton = (SignInButton) findViewById(R.id.btnLoginGoogle);

        signInButton.setSize(SignInButton.SIZE_WIDE);


        //Botones inicio de seccion
        btn_Login.setOnClickListener(view -> {
            ConexionInternet();
            loginUser();

        });

        signInButton.setOnClickListener((v) -> {
            ConexionInternet();
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            startActivityForResult(intent,RC_SING_IN);

        });


        servicio = new Intent(this, MyService.class);
        startService(servicio);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseA);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SING_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
       if (result.isSuccess()){
           AutenticacionFirebase(result.getSignInAccount());
       }else{
           Toast.makeText(this,"No se puede iniciar seccion", Toast.LENGTH_SHORT).show();
       }
    }

    private void goMainScreen() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(firebaseA != null){
            mAuth.removeAuthStateListener(firebaseA);
        }
    }

    // metodo para autenticacion con firebase
    private void AutenticacionFirebase(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){

                            Toast.makeText(getApplicationContext(),"No se puedo autenticar",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    //ingreso con validacion firebase
    private void loginUser(){
        String correo = Logemail.getText().toString();
        String contraseña = Logpass.getText().toString();

        if (TextUtils.isEmpty(correo)){
            Logemail.setError("Correo no puede estar vacio");
            Logemail.requestFocus();
        }else if (TextUtils.isEmpty(contraseña)){
            Logpass.setError("Contraseña no puede estar vacia");
            Logpass.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(correo,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful()){
                       Toast.makeText(LoginActivity.this, "Ingreso correctamente",Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(LoginActivity.this, MainActivity.class));
                   }else{
                       Toast.makeText(LoginActivity.this, "Error al iniciar "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                   }
                }
            });
        }
    }

    public void ConexionInternet(){
        ConnectivityManager connectivityManager =(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo!= null && networkInfo.isConnectedOrConnecting()){
            Log.d("", "Conexion establecida");

        }else{
            Toast.makeText(LoginActivity.this,"Error de Conexion", Toast.LENGTH_SHORT).show();
        }
    }

}
