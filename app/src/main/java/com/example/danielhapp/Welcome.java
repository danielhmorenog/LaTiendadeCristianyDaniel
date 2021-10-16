package com.example.danielhapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danielhapp.service.UtilsNetwork;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient googleApiClient;
    private FirebaseAuth mAuth;
    private GoogleSignInClient cli;

    Button salir,enviar;
    Spinner envios;
    String producto1, producto2, producto3,lista="";
    EditText etPedido;
    private static final String[] metodos = new String[]{"Rappi","Servientrega","Lo recojo","En bicicleta"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mAuth= FirebaseAuth.getInstance();

        salir = findViewById(R.id.btnSalir);
        enviar= findViewById(R.id.btnComprar);

        Bundle recibo = getIntent().getExtras();
        establecerPedido(recibo);

        etPedido = findViewById(R.id.etPedido);
        etPedido.setText(lista);

        envios = findViewById(R.id.s1);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, metodos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        envios.setAdapter(adaptador);

        salir.setOnClickListener(view ->  {
            onResume();
            cancelarPedido();
        });

        enviar.setOnClickListener(view -> {
            onResume();
            realizarEnvio();
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
    }

    public void cancelarPedido() {
        Toast.makeText(getApplicationContext(), "Cancelando su pedido", Toast.LENGTH_LONG).show();
        Intent ir = new Intent(this, MainActivity.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }

    public void realizarEnvio(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Su pedido:\n"+lista+"Fué enviado exitosamente a traves de "+envios.getSelectedItem().toString());
        alert.setPositiveButton("Realizar otro pedido", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Intent ir = new Intent(Welcome.this, Home.class);
                ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(ir);
            }
        });
        alert.show();


    }



    @Override
    protected void onResume() {
        super.onResume();
        if(UtilsNetwork.isOnline(this)){
            Log.d("", "Conexion establecida");
        } else{
            salir();
        }
    }

    public void salir(){
        mAuth.signOut();
        logOut();
        Toast.makeText(this,"No hay conexion a internet",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user= mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(Welcome.this, LoginActivity.class));
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
                    Toast.makeText(Welcome.this,"",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void establecerPedido(Bundle recibo){
        producto1 = recibo.getString("producto1");
        producto2 = recibo.getString("producto2");
        producto3 = recibo.getString("producto3");

        if(!producto1.equals("none")){
            lista+= producto1 + "\n";
        }
        if(!producto2.equals("none")){
            lista+= producto2 + "\n";
        }
        if(!producto3.equals("none")){
            lista+= producto3 + "\n";
        }
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}