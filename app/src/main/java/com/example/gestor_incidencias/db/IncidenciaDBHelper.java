package com.example.gestor_incidencias.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.example.gestor_incidencias.MainActivity;
import com.example.gestor_incidencias.clases.incidencia;
import com.example.gestor_incidencias.db.IncidenciaContract.*;

import static com.example.gestor_incidencias.db.IncidenciaContract.IncidenciaEntry.COLUMN_NAME_TITLE;
import static com.example.gestor_incidencias.db.IncidenciaContract.IncidenciaEntry.COLUMN_NAME_PRIORITY;
import static com.example.gestor_incidencias.db.IncidenciaContract.IncidenciaEntry.TABLE_NAME;

public class IncidenciaDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "incidencies.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + "(" + IncidenciaEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME_TITLE + " TEXT," + COLUMN_NAME_PRIORITY + " TEXT)";


    public IncidenciaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d("NESSX ANOUNCE","Database created!!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertIncidencia(SQLiteDatabase db, incidencia i){
        //Check the bd is open
        if (db.isOpen()){
            //Creation of the register for insert object with the content values
            ContentValues values = new ContentValues();

            //Insert the incidence getting all values
            values.put(COLUMN_NAME_TITLE, i.getNom());
            values.put(COLUMN_NAME_PRIORITY, i.getPrioritat());

            db.insert(TABLE_NAME, null, values);
        }else{
            Log.d("sql","Database is closed");
        }
    }

    //Obtener la lista de comentarios en la base de datos
    public ArrayList<incidencia> getIncidencias(){
        SQLiteDatabase db = this.getReadableDatabase();
        //Creamos el cursor
        ArrayList<incidencia> lista = new ArrayList<>();
        Cursor c = db.rawQuery("select title, prioritat from " +TABLE_NAME, null);
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                //Asignamos el valor en nuestras variables para crear un nuevo objeto Comentario
                String nom = c.getString(c.getColumnIndex("title"));
                String prioritat = c.getString(c.getColumnIndex("prioritat"));
                incidencia com = new incidencia(nom,prioritat);
                //Añadimos el comentario a la lista
                lista.add(com);
            } while (c.moveToNext());
        }

        //Cerramos el cursor
        c.close();
        return lista;
    }

}
