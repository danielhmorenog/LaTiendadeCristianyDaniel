package com.example.danielhapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    Button btn_salir;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//renderiza el xml de android


       btn_salir = findViewById(R.id.btnSalirR);
       mAuth = FirebaseAuth.getInstance();

       btn_salir.setOnClickListener(view -> {
           mAuth.signOut();
           startActivity(new Intent(MainActivity.this, LoginActivity.class));
       });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user= mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    public void navegar(View h){
        Intent ir = new Intent( this,Home.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }


}