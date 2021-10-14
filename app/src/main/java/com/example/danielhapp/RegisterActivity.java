package com.example.danielhapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText regEmail, regpass;
    TextView Registro;
    Button btn_registrado;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regEmail = findViewById(R.id.etRegEmail);
        regpass = findViewById(R.id.etRegPass);
        Registro = findViewById(R.id.tvLoginHere);
        btn_registrado = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();

        btn_registrado.setOnClickListener(view ->{
            createUser();
        });

        Registro.setOnClickListener(view ->{
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }

    private void createUser(){
        String correo = regEmail.getText().toString();
        String contraseña = regpass.getText().toString();

        if(TextUtils.isEmpty(correo)){
            regEmail.setError("Correo no puede estar vacio ");
            regEmail.requestFocus();
        }else if(TextUtils.isEmpty(contraseña)){
            regpass.setError("Contraseña no puede estar vacia");
            regpass.requestFocus();
        }else{
        mAuth.createUserWithEmailAndPassword(correo,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Usuario registrado correctamente",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }else{
                    Toast.makeText(RegisterActivity.this, "Error al crear Usuario "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        }
    }
}
