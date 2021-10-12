package com.example.danielhapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//renderiza el xml de android
        tv1= findViewById(R.id.et1);
        tv2= findViewById(R.id.et2);
    }

    public void navegar(View h){
        Intent ir = new Intent( this,Welcome.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        Bundle data = new Bundle();
        data.putString("password",tv2.getText().toString());
        data.putString("username",tv1.getText().toString());
        if (tv1.getText().toString().isEmpty() || tv2.getText().toString().isEmpty()){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Please pick a user and password");
            alert.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            alert.show();
        }else {
            ir.putExtras(data);
            startActivity(ir);
        }
    }
}