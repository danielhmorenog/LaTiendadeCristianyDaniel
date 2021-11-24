package com.example.danielhapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Administrador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
    }

    public void categoria(View h){
        Intent ir  = new Intent(this, Categoria.class);
        startActivity(ir);
    }

    public void compra(View h){
        Intent ir  = new Intent(this, Compra.class );
        startActivity(ir);
    }

    public void compraProducto(View h){
        Intent ir  = new Intent(this, CompraProducto.class );
        startActivity(ir);
    }

    public void seccion(View h){
        Intent ir  = new Intent(this, Seccion.class );
        startActivity(ir);
    }

    public void tienda(View h){
        Intent ir  = new Intent(this, Tienda.class );
        startActivity(ir);
    }

    public void producto(View h){
        Intent ir  = new Intent(this, Producto.class );
        startActivity(ir);
    }
}