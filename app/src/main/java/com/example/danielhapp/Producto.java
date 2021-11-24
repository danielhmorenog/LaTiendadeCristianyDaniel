package com.example.danielhapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.danielhapp.SQLite.CRUD;

public class Producto extends AppCompatActivity {

    private EditText id_producto, nombre, descripcion, precio, cantidad, unidad, id_categoria, nit_tienda;
    CRUD c = new CRUD();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        id_producto = (EditText) findViewById(R.id.et01producto);
        nombre = (EditText) findViewById(R.id.et02producto);
        descripcion = (EditText) findViewById(R.id.et03producto);
        precio = (EditText) findViewById(R.id.et04producto);
        cantidad = (EditText) findViewById(R.id.et05producto);
        unidad = (EditText) findViewById(R.id.et06producto);
        id_categoria = (EditText) findViewById(R.id.et07producto);
        nit_tienda = (EditText) findViewById(R.id.et08producto);
    }

    public void agregarProducto(View h){
        c.agregarProducto(id_producto.getText().toString(), nombre.getText().toString(), descripcion.getText().toString(),
                precio.getText().toString(), cantidad.getText().toString(), unidad.getText().toString(),
                id_categoria.getText().toString(), nit_tienda.getText().toString(), this);
        id_producto.setText("");
        nombre.setText("");
        descripcion.setText("");
        precio.setText("");
        cantidad.setText("");
        unidad.setText("");
        id_categoria.setText("");
        nit_tienda.setText("");
    }

    public void buscarProducto(View h){
        String fila[] = c.buscarProducto(id_producto.getText().toString(), this);
        nombre.setText(fila[0]);
        descripcion.setText(fila[1]);
        precio.setText(fila[2]);
        cantidad.setText(fila[3]);
        unidad.setText(fila[4]);
        id_categoria.setText(fila[5]);
        nit_tienda.setText(fila[6]);
    }

    public void modificarProducto(View h){
        c.modificarProducto(id_producto.getText().toString(), nombre.getText().toString(), descripcion.getText().toString(),
                precio.getText().toString(), cantidad.getText().toString(), unidad.getText().toString(),
                id_categoria.getText().toString(), nit_tienda.getText().toString(), this);
        id_producto.setText("");
        nombre.setText("");
        descripcion.setText("");
        precio.setText("");
        cantidad.setText("");
        unidad.setText("");
        id_categoria.setText("");
        nit_tienda.setText("");
    }

    public void eliminarProducto(View h){
        c.eliminarProducto(id_producto.getText().toString(), this);
        id_producto.setText("");
        nombre.setText("");
        descripcion.setText("");
        precio.setText("");
        cantidad.setText("");
        unidad.setText("");
        id_categoria.setText("");
        nit_tienda.setText("");
    }

    public void main(View h){
        Intent ir  = new Intent(this, Administrador.class );
        startActivity(ir);
    }
}