package com.example.danielhapp.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CRUD {

    public void agregarCategoria(String id_categoria, String nombre, String descripcion, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("id_categoria", id_categoria);
        registro.put("nombre", nombre);
        registro.put("descripcion", descripcion);
        baseDatos.insert("Categoria", null, registro);
        baseDatos.close();
        Toast.makeText(context, "Registro exitoso de la categoria", Toast.LENGTH_SHORT).show();
    }

    public String[] buscarCategoria(String id_categoria, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();
        Cursor cursor = baseDatos.rawQuery("Select nombre, descripcion from Categoria where id_categoria = " + id_categoria, null);
        String fila[] = new String[2];
        if(cursor.moveToFirst()){
            do{
                fila[0] = cursor.getString(0);
                fila[1] = cursor.getString(1);
            }while(cursor.moveToNext());
        }
        baseDatos.close();
        if(cursor.getCount() == 1){
            Toast.makeText(context,"Categoria buscada exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Categoria no existe", Toast.LENGTH_SHORT).show();
        }
        return fila;
    }

    public void modificarCategoria(String id_categoria, String nombre, String descripcion, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("id_categoria", id_categoria);
        registro.put("nombre", nombre);
        registro.put("descripcion", descripcion);
        int cantidad = baseDatos.update("Categoria", registro, "id_categoria = " + id_categoria, null);
        baseDatos.close();
        if(cantidad == 1){
            Toast.makeText(context,"Categoria modificada exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Categoria no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarCategoria(String id_categoria, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        int cantidad = baseDatos.delete("Categoria", "id_categoria = " + id_categoria, null);
        baseDatos.close();
        if(cantidad == 1){
            Toast.makeText(context,"Categoria eliminada exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Categoria no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void agregarCompra(String id_compra, String cedula, String total, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("id_compra", id_compra);
        registro.put("cedula", cedula);
        registro.put("total", total);
        baseDatos.insert("Compra", null, registro);
        baseDatos.close();
        Toast.makeText(context, "Registro exitoso de la compra", Toast.LENGTH_SHORT).show();
    }

    public String[] buscarCompra(String id_compra, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();
        Cursor cursor = baseDatos.rawQuery("Select cedula, total from Compra where id_compra = " + id_compra, null);
        String fila[] = new String[2];
        if(cursor.moveToFirst()){
            do{
                fila[0] = cursor.getString(0);
                fila[1] = cursor.getString(1);
            }while(cursor.moveToNext());
        }
        baseDatos.close();
        if(cursor.getCount() == 1){
            Toast.makeText(context,"Compra buscada exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Compra no existe", Toast.LENGTH_SHORT).show();
        }
        return fila;
    }

    public void modificarCompra(String id_compra, String cedula, String total, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("id_compra", id_compra);
        registro.put("cedula", cedula);
        registro.put("total", total);
        int cantidad = baseDatos.update("Compra", registro, "id_compra = " + id_compra, null);
        baseDatos.close();
        if(cantidad == 1){
            Toast.makeText(context,"Compra modificada exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Compra no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarCompra(String id_compra, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        int cantidad = baseDatos.delete("Compra", "id_compra = " + id_compra, null);
        baseDatos.close();
        if(cantidad == 1){
            Toast.makeText(context,"Compra eliminada exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Compra no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void agregarCompraProducto(String id_compraProducto, String id_producto, String id_compra, String cantidad, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("id_compraProducto", id_compraProducto);
        registro.put("id_producto", id_producto);
        registro.put("id_compra", id_compra);
        registro.put("cantidad", cantidad);
        baseDatos.insert("Compra_Producto", null, registro);
        baseDatos.close();
        Toast.makeText(context, "Registro exitoso de la compraProducto", Toast.LENGTH_SHORT).show();
    }

    public String[] buscarCompraProducto(String id_compraProducto, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();
        Cursor cursor = baseDatos.rawQuery("Select id_producto, id_compra, cantidad from Compra_Producto where id_compraProducto = " + id_compraProducto, null);
        String fila[] = new String[3];
        if(cursor.moveToFirst()){
            do{
                fila[0] = cursor.getString(0);
                fila[1] = cursor.getString(1);
                fila[2] = cursor.getString(2);
            }while(cursor.moveToNext());
        }
        baseDatos.close();
        if(cursor.getCount() == 1){
            Toast.makeText(context,"CompraProducto buscada exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"CompraProducto no existe", Toast.LENGTH_SHORT).show();
        }
        return fila;
    }

    public void modificarCompraProducto(String id_compraProducto, String id_producto, String id_compra, String cantidad, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("id_compraProducto", id_compraProducto);
        registro.put("id_producto", id_producto);
        registro.put("id_compra", id_compra);
        registro.put("cantidad", cantidad);
        int cantidad1 = baseDatos.update("Compra_Producto", registro, "id_compraProducto = " + id_compraProducto, null);
        baseDatos.close();
        if(cantidad1 == 1){
            Toast.makeText(context,"CompraProducto modificada exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"CompraProducto no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarCompraProducto(String id_compraProducto, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        int cantidad1 = baseDatos.delete("Compra_Producto", "id_compraProducto = " + id_compraProducto, null);
        baseDatos.close();
        if(cantidad1 == 1){
            Toast.makeText(context,"CompraProducto eliminada exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"CompraProducto no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void agregarProducto(String id_Producto, String nombre, String descripcion, String precio, String cantidad,
            String unidad, String id_categoria, String nit_tienda, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("id_Producto", id_Producto);
        registro.put("nombre", nombre);
        registro.put("descripcion", descripcion);
        registro.put("precio", precio);
        registro.put("cantidad", cantidad);
        registro.put("unidad", unidad);
        registro.put("id_categoria", id_categoria);
        registro.put("nit_tienda", nit_tienda);
        baseDatos.insert("Producto", null, registro);
        baseDatos.close();
        Toast.makeText(context, "Registro exitoso del Producto", Toast.LENGTH_SHORT).show();
    }

    public String[] buscarProducto(String id_Producto, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();
        Cursor cursor = baseDatos.rawQuery("Select nombre, descripcion, precio, cantidad, unidad, id_categoria, nit_tienda from Producto where id_Producto = " + id_Producto, null);
        String fila[] = new String[7];
        if(cursor.moveToFirst()){
            do{
                fila[0] = cursor.getString(0);
                fila[1] = cursor.getString(1);
                fila[2] = cursor.getString(2);
                fila[3] = cursor.getString(3);
                fila[4] = cursor.getString(4);
                fila[5] = cursor.getString(5);
                fila[6] = cursor.getString(6);
            }while(cursor.moveToNext());
        }
        baseDatos.close();
        if(cursor.getCount() == 1){
            Toast.makeText(context,"Producto buscado exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Producto no existe", Toast.LENGTH_SHORT).show();
        }
        return fila;
    }

    public void modificarProducto(String id_Producto, String nombre, String descripcion, String precio, String cantidad,
                                  String unidad, String id_categoria, String nit_tienda, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("id_Producto", id_Producto);
        registro.put("nombre", nombre);
        registro.put("descripcion", descripcion);
        registro.put("precio", precio);
        registro.put("cantidad", cantidad);
        registro.put("unidad", unidad);
        registro.put("id_categoria", id_categoria);
        registro.put("nit_tienda", nit_tienda);
        int cantidad1 = baseDatos.update("Producto", registro, "id_Producto = " + id_Producto, null);
        baseDatos.close();
        if(cantidad1 == 1){
            Toast.makeText(context,"Producto modificado exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Producto no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarProducto(String id_Producto, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        int cantidad1 = baseDatos.delete("Producto", "id_Producto = " + id_Producto, null);
        baseDatos.close();
        if(cantidad1 == 1){
            Toast.makeText(context,"Producto eliminado exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Producto no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void agregarSeccion(String cedula, String nombre, String apellido, String direccion, String telefono,
                                String correo, String password, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("cedula", cedula);
        registro.put("nombre", nombre);
        registro.put("apellido", apellido);
        registro.put("direccion", direccion);
        registro.put("telefono", telefono);
        registro.put("correo", correo);
        registro.put("password", password);
        baseDatos.insert("Sesion", null, registro);
        baseDatos.close();
        Toast.makeText(context, "Registro exitoso de sesion", Toast.LENGTH_SHORT).show();
    }

    public String[] buscarSeccion(String cedula, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();
        Cursor cursor = baseDatos.rawQuery("Select nombre, apellido, direccion, telefono, correo, password from Sesion where cedula = " + cedula, null);
        String fila[] = new String[6];
        if(cursor.moveToFirst()){
            do{
                fila[0] = cursor.getString(0);
                fila[1] = cursor.getString(1);
                fila[2] = cursor.getString(2);
                fila[3] = cursor.getString(3);
                fila[4] = cursor.getString(4);
                fila[5] = cursor.getString(5);
            }while(cursor.moveToNext());
        }
        baseDatos.close();
        if(cursor.getCount() == 1){
            Toast.makeText(context,"Sesion buscado exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Sesion no existe", Toast.LENGTH_SHORT).show();
        }
        return fila;
    }

    public void modificarSeccion(String cedula, String nombre, String apellido, String direccion, String telefono,
                                 String correo, String password, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("cedula", cedula);
        registro.put("nombre", nombre);
        registro.put("apellido", apellido);
        registro.put("direccion", direccion);
        registro.put("telefono", telefono);
        registro.put("correo", correo);
        registro.put("password", password);
        int cantidad1 = baseDatos.update("Sesion", registro, "cedula = " + cedula, null);
        baseDatos.close();
        if(cantidad1 == 1){
            Toast.makeText(context,"Sesion modificado exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Sesion no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarSeccion(String cedula, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        int cantidad1 = baseDatos.delete("Sesion", "cedula = " + cedula, null);
        baseDatos.close();
        if(cantidad1 == 1){
            Toast.makeText(context,"Sesion eliminado exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Sesion no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void agregarTienda(String nit, String nombre, String descripcion, String direccion, String telefono, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nit", nit);
        registro.put("nombre", nombre);
        registro.put("descripcion", descripcion);
        registro.put("direccion", direccion);
        registro.put("telefono", telefono);
        baseDatos.insert("Tienda", null, registro);
        baseDatos.close();
        Toast.makeText(context, "Registro exitoso de tienda", Toast.LENGTH_SHORT).show();
    }

    public String[] buscarTienda(String nit, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();
        Cursor cursor = baseDatos.rawQuery("Select nombre, descripcion, direccion, telefono from Tienda where nit = " + nit, null);
        String fila[] = new String[4];
        if(cursor.moveToFirst()){
            do{
                fila[0] = cursor.getString(0);
                fila[1] = cursor.getString(1);
                fila[2] = cursor.getString(2);
                fila[3] = cursor.getString(3);
            }while(cursor.moveToNext());
        }
        baseDatos.close();
        if(cursor.getCount() == 1){
            Toast.makeText(context,"Tienda buscada exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Tienda no existe", Toast.LENGTH_SHORT).show();
        }
        return fila;
    }

    public void modificarTienda(String nit, String nombre, String descripcion, String direccion, String telefono, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nit", nit);
        registro.put("nombre", nombre);
        registro.put("descripcion", descripcion);
        registro.put("direccion", direccion);
        registro.put("telefono", telefono);
        int cantidad1 = baseDatos.update("Tienda", registro, "nit = " + nit, null);
        baseDatos.close();
        if(cantidad1 == 1){
            Toast.makeText(context,"Tienda modificado exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Tienda no existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarTienda(String nit, AppCompatActivity context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "proyecto_moviles", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        int cantidad1 = baseDatos.delete("Tienda", "nit = " + nit, null);
        baseDatos.close();
        if(cantidad1 == 1){
            Toast.makeText(context,"Tienda eliminada exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Tienda no existe", Toast.LENGTH_SHORT).show();
        }
    }
}
