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

    public ArrayList<incidencia> listIncidencia(){
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<incidencia> inc = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                String nom = cursor.getString(1);
                String prioritat = cursor.getString(2);
                inc.add(new incidencia(nom, prioritat));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return inc;
    }

}
