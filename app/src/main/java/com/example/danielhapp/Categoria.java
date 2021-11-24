package com.example.danielhapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.danielhapp.SQLite.CRUD;

public class Categoria extends AppCompatActivity {

    private EditText id_categoria, nombre, descripcion;
    CRUD c = new CRUD();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        id_categoria = (EditText) findViewById(R.id.et01categoria);
        nombre = (EditText) findViewById(R.id.et02categoria);
        descripcion = (EditText) findViewById(R.id.et03categoria);
    }

    public void agregarCategoria(View h){
        c.agregarCategoria(id_categoria.getText().toString(), nombre.getText().toString(), descripcion.getText().toString(), this);
        id_categoria.setText("");
        nombre.setText("");
        descripcion.setText("");
    }

    public void buscarCategoria(View h){
        String fila[] = c.buscarCategoria(id_categoria.getText().toString(), this);
        nombre.setText(fila[0]);
        descripcion.setText(fila[1]);
    }

    public void modificarCategoria(View h){
        c.modificarCategoria(id_categoria.getText().toString(), nombre.getText().toString(), descripcion.getText().toString(), this);
        id_categoria.setText("");
        nombre.setText("");
        descripcion.setText("");
    }

    public void eliminarCategoria(View h){
        c.eliminarCategoria(id_categoria.getText().toString(), this);
        id_categoria.setText("");
        nombre.setText("");
        descripcion.setText("");
    }

    public void main(View h){
        Intent ir  = new Intent(this, Administrador.class );
        startActivity(ir);
    }
}