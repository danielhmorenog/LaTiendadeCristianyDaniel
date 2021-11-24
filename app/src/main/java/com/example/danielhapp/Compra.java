package com.example.danielhapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.danielhapp.SQLite.CRUD;

public class Compra extends AppCompatActivity {

    private EditText id_compra, cedula, total;
    CRUD c = new CRUD();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        id_compra = (EditText) findViewById(R.id.et01compra);
        cedula = (EditText) findViewById(R.id.et02compra);
        total = (EditText) findViewById(R.id.et03compra);
    }

    public void agregarCompra(View h){
        c.agregarCompra(id_compra.getText().toString(), cedula.getText().toString(), total.getText().toString(), this);
        id_compra.setText("");
        cedula.setText("");
        total.setText("");
    }

    public void buscarCompra(View h){
        String fila[] = c.buscarCompra(id_compra.getText().toString(), this);
        cedula.setText(fila[0]);
        total.setText(fila[1]);
    }

    public void modificarCompra(View h){
        c.modificarCompra(id_compra.getText().toString(), cedula.getText().toString(), total.getText().toString(), this);
        id_compra.setText("");
        cedula.setText("");
        total.setText("");
    }

    public void eliminarCompra(View h){
        c.eliminarCompra(id_compra.getText().toString(), this);
        id_compra.setText("");
        cedula.setText("");
        total.setText("");
    }

    public void main(View h){
        Intent ir  = new Intent(this, Administrador.class );
        startActivity(ir);
    }
}