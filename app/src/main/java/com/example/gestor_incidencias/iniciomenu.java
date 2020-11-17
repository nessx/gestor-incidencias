package com.example.gestor_incidencias;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestor_incidencias.clases.RecyclerViewAdapter;
import com.example.gestor_incidencias.clases.agregar;
import com.example.gestor_incidencias.clases.incidencia;
import com.example.gestor_incidencias.clases.usuario;
import com.example.gestor_incidencias.db.IncidenciaDBHelper;

import java.util.ArrayList;
import java.util.List;


public class iniciomenu extends AppCompatActivity {
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private AlertDialog.Builder alertDialog;
    private EditText edt_item;
    private View view;
    private boolean add = false;
    private int edit_position;
    private Paint p = new Paint();

    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;

    TextView userview;
    protected Fragment[] menuFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_inicio);

        //Creation of the dbHelper
        dbHelper = new IncidenciaDBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        usuario user = new usuario();
        userview = findViewById(R.id.userview);
        userview.setText(getResources().getString(R.string.dialog_welcome)+" "+user.getuser().toUpperCase());
    }

}