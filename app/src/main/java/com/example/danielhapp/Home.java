package com.example.danielhapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    CheckBox cbProducto1,cbProducto2,cbProducto3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cbProducto1=findViewById(R.id.cbProducto1);
        cbProducto2=findViewById(R.id.cbProducto2);
        cbProducto3=findViewById(R.id.cbProducto3);

    }

    public void realizarPedido(View h){
        Intent ir = new Intent( this,Welcome.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        Bundle data = new Bundle();
        if(cbProducto1.isChecked()==false && cbProducto2.isChecked()==false && cbProducto3.isChecked()==false){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Debe seleccionar por lo menos un producto");
            alert.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            alert.show();
        }else{
        if (cbProducto1.isChecked()){
            data.putString("producto1","Leche");
        }else {data.putString("producto1","none");}
        if (cbProducto2.isChecked()){
            data.putString("producto2","Gaseosa");
        }else {data.putString("producto2","none");}
        if (cbProducto3.isChecked()){
            data.putString("producto3","Cereal");
        }else {data.putString("producto3","none");}
            ir.putExtras(data);
            startActivity(ir);
    }}

    public void cancelarPedido(View h) {
        Toast.makeText(getApplicationContext(), "Cancelando su pedido", Toast.LENGTH_LONG).show();
        Intent ir = new Intent(this, MainActivity.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }
}