package com.example.danielhapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Welcome extends AppCompatActivity {

    Spinner envios;
    private static final String[] metodos = new String[]{"Rappi","Servientrega","Lo recojo","En bicicleta"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        envios = findViewById(R.id.s1);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, metodos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        envios.setAdapter(adaptador);
    }
}