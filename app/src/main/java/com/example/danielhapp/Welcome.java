package com.example.danielhapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    Spinner envios;
    String producto1, producto2, producto3,lista="";
    EditText etPedido;
    private static final String[] metodos = new String[]{"Rappi","Servientrega","Lo recojo","En bicicleta"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Bundle recibo = getIntent().getExtras();
        producto1 = recibo.getString("producto1");
        producto2 = recibo.getString("producto2");
        producto3 = recibo.getString("producto3");

        if(producto1!="none"){
            lista+= producto1 + "\n";
        }
        if(producto2!="none"){
            lista+= producto2 + "\n";
        }
        if(producto3!="none"){
            lista+= producto3 + "\n";
        }

        etPedido = findViewById(R.id.etPedido);
        etPedido.setText(lista);

        envios = findViewById(R.id.s1);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, metodos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        envios.setAdapter(adaptador);
    }

    public void cancelarPedido(View h) {
        Toast.makeText(getApplicationContext(), "Cancelando su pedido", Toast.LENGTH_LONG).show();
        Intent ir = new Intent(this, MainActivity.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }
}