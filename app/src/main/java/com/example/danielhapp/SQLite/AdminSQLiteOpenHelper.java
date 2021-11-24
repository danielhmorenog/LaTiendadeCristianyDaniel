package com.example.danielhapp.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Categoria( id_categoria INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre CHAR(40) NOT NULL, descripcion VARCHAR(200))");
        sqLiteDatabase.execSQL("CREATE TABLE Sesion(\n" +
                "\tcedula VARCHAR(20) NOT NULL,\n" +
                "\tnombre VARCHAR(40) NOT NULL,\n" +
                "\tapellido VARCHAR(40),\n" +
                "\tdireccion VARCHAR(40) NOT NULL,\n" +
                "\ttelefono VARCHAR(15),\n" +
                "\tcorreo VARCHAR(60) NOT NULL,\n" +
                "\tpassword VARCHAR(200) NOT NULL,\n" +
                "\tPRIMARY KEY(cedula)\n" +
                ");");
        sqLiteDatabase.execSQL("CREATE TABLE Tienda(\n" +
                "\tnit VARCHAR(30) NOT NULL,\n" +
                "\tnombre VARCHAR(50) NOT NULL,\n" +
                "\tdescripcion VARCHAR(200),\n" +
                "\tdireccion VARCHAR(40) NOT NULL,\n" +
                "\ttelefono VARCHAR(15),\n" +
                "\tPRIMARY KEY (nit)\n" +
                ");");
        sqLiteDatabase.execSQL("CREATE TABLE Producto(\n" +
                "\tid_producto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\tnombre VARCHAR(30) NOT NULL,\n" +
                "\tdescripcion VARCHAR(100),\n" +
                "\tprecio INT NOT NULL,\n" +
                "\tcantidad INT NOT NULL,\n" +
                "\tunidad VARCHAR(15) NOT NULL,\n" +
                "\tid_categoria INT NOT NULL,\n" +
                "\tnit_tienda VARCHAR(30) NOT NULL,\n" +
                "\tFOREIGN KEY (id_categoria) REFERENCES Categoria (id_categoria),\n" +
                "\tFOREIGN KEY (nit_tienda) REFERENCES Tienda(nit)\n" +
                ");");
        sqLiteDatabase.execSQL("CREATE TABLE Compra(\n" +
                "\tid_compra INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\tcedula VARCHAR(20) NOT NULL,\n" +
                "\ttotal int NOT NULL,\n" +
                "\tFOREIGN KEY (cedula) REFERENCES Sesion (cedula)\n" +
                "\t\n" +
                ");");
        sqLiteDatabase.execSQL("CREATE TABLE Compra_Producto(\n" +
                "\tid_compraProducto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "\tid_producto INT NOT NULL,\n" +
                "\tid_compra INT NOT NULL,\n" +
                "\tcantidad INT NOT NULL,\n" +
                "\tFOREIGN KEY (id_producto) REFERENCES Producto (id_producto),\n" +
                "\tFOREIGN KEY (id_compra) REFERENCES Compra (id_compra)\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
