package com.example.danielhapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.danielhapp.SQLite.CRUD;

public class Seccion extends AppCompatActivity {

    private EditText cedula, nombre, apellido, direccion, telefono, correo, password;
    CRUD c = new CRUD();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seccion);
        cedula = (EditText) findViewById(R.id.et01seccion);
        nombre = (EditText) findViewById(R.id.et02seccion);
        apellido = (EditText) findViewById(R.id.et03seccion);
        direccion = (EditText) findViewById(R.id.et04seccion);
        telefono = (EditText) findViewById(R.id.et05seccion);
        correo = (EditText) findViewById(R.id.et06seccion);
        password = (EditText) findViewById(R.id.et07seccion);
    }

    public void agregarSeccion(View h){
        c.agregarSeccion(cedula.getText().toString(), nombre.getText().toString(), apellido.getText().toString(),
                direccion.getText().toString(), telefono.getText().toString(), correo.getText().toString(),
                password.getText().toString(), this);
        cedula.setText("");
        nombre.setText("");
        apellido.setText("");
        direccion.setText("");
        telefono.setText("");
        correo.setText("");
        password.setText("");
    }

    public void buscarSeccion(View h){
        String fila[] = c.buscarSeccion(cedula.getText().toString(), this);
        nombre.setText(fila[0]);
        apellido.setText(fila[1]);
        direccion.setText(fila[2]);
        telefono.setText(fila[3]);
        correo.setText(fila[4]);
        password.setText(fila[5]);
    }

    public void modificarSeccion(View h){
        c.modificarSeccion(cedula.getText().toString(), nombre.getText().toString(), apellido.getText().toString(),
                direccion.getText().toString(), telefono.getText().toString(), correo.getText().toString(),
                password.getText().toString(), this);
        cedula.setText("");
        nombre.setText("");
        apellido.setText("");
        direccion.setText("");
        telefono.setText("");
        correo.setText("");
        password.setText("");
    }

    public void eliminarSeccion(View h){
        c.eliminarSeccion(cedula.getText().toString(), this);
        cedula.setText("");
        nombre.setText("");
        apellido.setText("");
        direccion.setText("");
        telefono.setText("");
        correo.setText("");
        password.setText("");
    }

    public void main(View h){
        Intent ir  = new Intent(this, Administrador.class );
        startActivity(ir);
    }
}