package com.example.danielhapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.danielhapp.SQLite.CRUD;

public class CompraProducto extends AppCompatActivity {

    private EditText id_compraProducto, id_producto, id_compra, cantidad;
    CRUD c = new CRUD();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_producto);
        id_compraProducto = (EditText) findViewById(R.id.et01compraProducto);
        id_producto = (EditText) findViewById(R.id.et02compraProducto);
        id_compra = (EditText) findViewById(R.id.et03compraProducto);
        cantidad = (EditText) findViewById(R.id.et04compraProducto);
    }

    public void agregarCompraProducto(View h){
        c.agregarCompraProducto(id_compraProducto.getText().toString(), id_producto.getText().toString(), id_compra.getText().toString(), cantidad.getText().toString(), this);
        id_compraProducto.setText("");
        id_producto.setText("");
        id_compra.setText("");
        cantidad.setText("");
    }

    public void buscarCompraProducto(View h){
        String fila[] = c.buscarCompraProducto(id_compraProducto.getText().toString(), this);
        id_producto.setText(fila[0]);
        id_compra.setText(fila[1]);
        cantidad.setText(fila[2]);
    }

    public void modificarCompraProducto(View h){
        c.modificarCompraProducto(id_compraProducto.getText().toString(), id_producto.getText().toString(), id_compra.getText().toString(), cantidad.getText().toString(), this);
        id_compraProducto.setText("");
        id_producto.setText("");
        id_compra.setText("");
        cantidad.setText("");
    }

    public void eliminarCompraProducto(View h){
        c.eliminarCompraProducto(id_compraProducto.getText().toString(), this);
        id_compraProducto.setText("");
        id_producto.setText("");
        id_compra.setText("");
        cantidad.setText("");
    }

    public void main(View h){
        Intent ir  = new Intent(this, Administrador.class );
        startActivity(ir);
    }
}