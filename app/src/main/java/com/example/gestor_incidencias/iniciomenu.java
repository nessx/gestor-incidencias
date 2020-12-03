package com.example.gestor_incidencias;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gestor_incidencias.clases.RecyclerViewAdapter;
import com.example.gestor_incidencias.clases.agregar;
import com.example.gestor_incidencias.clases.listar;
import com.example.gestor_incidencias.clases.settings;
import com.example.gestor_incidencias.clases.usuario;
import com.example.gestor_incidencias.db.IncidenciaDBHelper;
import com.google.android.material.navigation.NavigationView;
import com.example.gestor_incidencias.clases.spref_manager;

import java.util.Locale;


public class iniciomenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        DrawerLayout.DrawerListener {
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
    private DrawerLayout drawerLayout;
    private Fragment fragment;


    protected Fragment[] menuFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_inicio);

        //Creation of the dbHelper
        dbHelper = new IncidenciaDBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        /*usuario user = new usuario();
        TextView userview = (TextView)findViewById(R.id.header_title);
        userview.setText(getResources().getString(R.string.dialog_welcome)+" "+user.getuser());*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.fragmentID);
        navigationView.setNavigationItemSelectedListener(this);

        MenuItem menuItem = navigationView.getMenu().getItem(0);
        onNavigationItemSelected(menuItem);
        menuItem.setChecked(true);

        drawerLayout.addDrawerListener(this);

    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int title;
        switch (menuItem.getItemId()) {
            case R.id.afegir:
                fragment = new agregar();
                break;
            case R.id.llistar:
                fragment = new listar();
                break;
            case R.id.eliminar:
                confirm();
                break;
            case R.id.ajustes:
                fragment = new settings();
                break;
            default:
                throw new IllegalArgumentException("menu option not implemented!!");
        }

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.bottom_nav_enter, R.anim.bottom_nav_exit)
                .replace(R.id.home_content, fragment)
                .commit();

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onDrawerSlide(@NonNull View view, float v) {
        //cambio en la posición del drawer
    }

    @Override
    public void onDrawerOpened(@NonNull View view) {
        //el drawer se ha abierto completamente
    }

    @Override
    public void onDrawerClosed(@NonNull View view) {
        //el drawer se ha cerrado completamente
    }

    @Override
    public void onDrawerStateChanged(int i) {
        //cambio de estado, puede ser STATE_IDLE, STATE_DRAGGING or STATE_SETTLING
    }

    public void confirm() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));

        // set title
        alertDialogBuilder.setTitle(R.string.dialog_cln_tl);

        // set dialog message
        alertDialogBuilder
                .setMessage(R.string.dialog_mensaje)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_aceptar,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dbHelper.delete();
                        Toast.makeText(getApplicationContext(), getString(R.string.dialog_del_msg),
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.dialog_cancelar,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Toast.makeText(getApplicationContext(), getString(R.string.dialog_cln_msg),
                                Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }



}