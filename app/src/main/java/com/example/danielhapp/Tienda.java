package com.example.danielhapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.danielhapp.SQLite.CRUD;

public class Tienda extends AppCompatActivity {

    private EditText nit, nombre, descripcion, direccion, telefono;
    CRUD c = new CRUD();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        nit = (EditText) findViewById(R.id.et01tienda);
        nombre = (EditText) findViewById(R.id.et02tienda);
        descripcion = (EditText) findViewById(R.id.et03tienda);
        direccion = (EditText) findViewById(R.id.et04tienda);
        telefono = (EditText) findViewById(R.id.et05tienda);
    }

    public void agregarTienda(View h){
        c.agregarTienda(nit.getText().toString(), nombre.getText().toString(), descripcion.getText().toString(),
                direccion.getText().toString(), telefono.getText().toString(), this);
        nit.setText("");
        nombre.setText("");
        descripcion.setText("");
        direccion.setText("");
        telefono.setText("");
    }

    public void buscarTienda(View h){
        String fila[] = c.buscarTienda(nit.getText().toString(), this);
        nombre.setText(fila[0]);
        descripcion.setText(fila[1]);
        direccion.setText(fila[2]);
        telefono.setText(fila[3]);
    }

    public void modificarTienda(View h){
        c.modificarTienda(nit.getText().toString(), nombre.getText().toString(), descripcion.getText().toString(),
                direccion.getText().toString(), telefono.getText().toString(), this);
        nit.setText("");
        nombre.setText("");
        descripcion.setText("");
        direccion.setText("");
        telefono.setText("");
    }

    public void eliminarTienda(View h) {
        c.eliminarTienda(nit.getText().toString(), this);
        nit.setText("");
        nombre.setText("");
        descripcion.setText("");
        direccion.setText("");
        telefono.setText("");
    }

    public void main(View h){
        Intent ir  = new Intent(this, Administrador.class );
        startActivity(ir);
    }
}