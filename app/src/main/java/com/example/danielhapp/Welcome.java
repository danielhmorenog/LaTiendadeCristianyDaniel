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
        //envios.setOnItemClickListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}