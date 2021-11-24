package com.example.danielhapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
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

public class Home extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient googleApiClient;
    private FirebaseAuth mAuth;
    private GoogleSignInClient cli;

    CheckBox cbProducto1,cbProducto2,cbProducto3, cbProducto4, cbProducto5, cbProducto6, cbProducto7, cbProducto8;
    ImageButton Pedir,Cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        cbProducto1=findViewById(R.id.cbProducto1);
        cbProducto2=findViewById(R.id.cbProducto2);
        cbProducto3=findViewById(R.id.cbProducto3);
        cbProducto4=findViewById(R.id.cbProducto4);
        cbProducto5=findViewById(R.id.cbProducto5);
        cbProducto6=findViewById(R.id.cbProducto6);
        cbProducto7=findViewById(R.id.cbProducto7);
        cbProducto8=findViewById(R.id.cbProducto8);

        Pedir = findViewById(R.id.btnPedir);
        Cancelar = findViewById(R.id.btnCancelar);

        Pedir.setOnClickListener(view ->  {
            onResume();
            realizarPedido();
        });

        Cancelar.setOnClickListener(view ->  {
            onResume();
            cancelarPedido();
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

    }

    public void realizarPedido(){

        //CRUD crud = new CRUD();

        Intent ir = new Intent( this,Welcome.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        Bundle data = new Bundle();
        if(cbProducto1.isChecked()==false && cbProducto2.isChecked()==false && cbProducto3.isChecked()==false &&
                cbProducto4.isChecked()==false && cbProducto5.isChecked()==false && cbProducto6.isChecked()==false &&
                    cbProducto7.isChecked()==false && cbProducto8.isChecked()==false){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Debe seleccionar por lo menos un producto");
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            alert.show();
        }else{
        if (cbProducto1.isChecked()){
            data.putString("producto1","Six Pack Cervezas");
        }else {data.putString("producto1","none");}
        if (cbProducto2.isChecked()){
            data.putString("producto2","Leche Entera 1000ml");
        }else {data.putString("producto2","none");}
        if (cbProducto3.isChecked()){
            data.putString("producto3","Queso Tapioca");
        }else {data.putString("producto3","none");}
        if (cbProducto4.isChecked()){
            data.putString("producto4","CocaCola 1.5L");
        }else {data.putString("producto4","none");}
        if (cbProducto5.isChecked()){
            data.putString("producto5","Frijoles Rojos");
        }else {data.putString("producto5","none");}
        if (cbProducto6.isChecked()){
            data.putString("producto6","Azucar 500gr");
        }else {data.putString("producto6","none");}
        if (cbProducto7.isChecked()){
            data.putString("producto7","Platano Maduro");
        }else {data.putString("producto7","none");}
        if (cbProducto8.isChecked()){
            data.putString("producto8","Atún Vancamps");
        }else {data.putString("producto8","none");}
            ir.putExtras(data);
            startActivity(ir);
    }}

    public void cancelarPedido() {
        Toast.makeText(getApplicationContext(), "Cancelando su pedido", Toast.LENGTH_LONG).show();
        Intent ir = new Intent(this, MainActivity.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
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
            startActivity(new Intent(Home.this, LoginActivity.class));
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
                    Toast.makeText(Home.this,"",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}