package com.mlaguirre.mtech.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.mlaguirre.mtech.Registro;

/**
 * Created by TecnoMakro on 20/03/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mtech.db";
    private static final String TABLE_NAME = "cliente";
    private static final String COLUMN_CEDULA = "cedula";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_APELLIDO = "apellido";
    private static final String COLUMN_DIRECCION = "direccion";
    private static final String COLUMN_TELEFONO = "telefono";
    private static final String COLUMN_EMAIL = "email";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table cliente(cedula text not null," +
            "nombre text not null, apellido text not null, direccion text not null," +
            " telefono text not null, email text not null);";

    //constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void agregarCliente(Cliente c){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_CEDULA, c.getCedula());
        values.put(COLUMN_NOMBRE, c.getNombre());
        values.put(COLUMN_APELLIDO, c.getApellido());
        values.put(COLUMN_DIRECCION, c.getDireccion());
        values.put(COLUMN_TELEFONO, c.getTelefono());
        values.put(COLUMN_EMAIL, c.getEmail());

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public Cliente buscarCliente(String ced){
        Cliente cliente = new Cliente();
        db = this.getReadableDatabase();

        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a;

        if(cursor.moveToFirst()){
            do {
                a = cursor.getString(0);
                if (a.equals(ced)) {
                    cliente.setNombre(cursor.getString(1));
                    cliente.setApellido(cursor.getString(2));
                    cliente.setDireccion(cursor.getString(3));
                    cliente.setTelefono(cursor.getString(4));
                    cliente.setEmail(cursor.getString(5));
                    break;
                }
            }while (cursor.moveToNext());
        }
        db.close();
        return cliente;
    }

    public String modificarCliente(Cliente c){

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String msg="";

        String ced1 = c.getCedula();

        values.put(COLUMN_NOMBRE, c.getNombre());
        values.put(COLUMN_APELLIDO, c.getApellido());
        values.put(COLUMN_DIRECCION, c.getDireccion());
        values.put(COLUMN_TELEFONO, c.getTelefono());
        values.put(COLUMN_EMAIL, c.getEmail());

        int cant = db.update(TABLE_NAME, values, "cedula="+ ced1, null);
        db.close();
        if (cant ==1){
            msg = "se modificaron los datos";
        }else {
            msg = "no existe una persona con dicho documento";
        }
        return msg;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXIST"+ TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}

