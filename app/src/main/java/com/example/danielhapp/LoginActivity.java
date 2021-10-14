package com.example.danielhapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {


    TextInputEditText Logemail,Logpass;
    TextView RegisterHere;
    Button btn_Login;
    FirebaseAuth mAuth;

    private GoogleSignInClient mGoogleCli;
    private final static int RC_SING_IN = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        Logemail = findViewById(R.id.etLoginEmail);
        Logpass = findViewById(R.id.etLoginPass);
        RegisterHere = findViewById(R.id.tvRegisterHere);
        btn_Login = findViewById(R.id.btnLogin);


        mAuth = FirebaseAuth.getInstance();

        btn_Login.setOnClickListener(view -> {
            loginUser();
        });
        RegisterHere.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        // inicio de seccion con google
        
        Crearsolicitud();



    }

    //solicitud google
    private void Crearsolicitud() {
    //Configuracion de Inicico de sesion google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SING_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
          //  handleSignInResult(task);
        }
    }
/*
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

*/
    private void signIn() {
        Intent signInIntent = mGoogleCli.getSignInIntent();
        startActivityForResult(signInIntent, RC_SING_IN);
    }

    // metodo para autenticacion con firebase
    private void AutenticacionFirebase(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // sis inicio de seccion
                            FirebaseUser user = mAuth.getCurrentUser();//Obtenemos el usuario

                            //si el usuario inicia sesion
                            if(task.getResult().getAdditionalUserInfo().isNewUser()){

                                String uid = user.getUid();
                                String correo = user.getEmail();
                            }

                            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this,"",Toast.LENGTH_SHORT).show();
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

}
