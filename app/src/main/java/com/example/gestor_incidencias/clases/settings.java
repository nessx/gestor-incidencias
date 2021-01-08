package com.example.gestor_incidencias.clases;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.gestor_incidencias.clases.spref_manager;

import com.example.gestor_incidencias.R;
import com.example.gestor_incidencias.db.IncidenciaDBHelper;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class settings extends Fragment {
    public Spinner languaje;

    private spref_manager s_preferences;
    private static Locale myLocale;

    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;


    public settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View settings = inflater.inflate(R.layout.fragment_settings, container, false);
        final Button btnsave = settings.findViewById(R.id.btnsave);
        final Button reset = settings.findViewById(R.id.reset);
        final Button resetinc = settings.findViewById(R.id.resetinc);
        TextView title = settings.findViewById(R.id.set_title);

        title.setText(R.string.menu_ajustes);
        reset.setText(R.string.reset);
        btnsave.setText(R.string.save);
        resetinc.setText(R.string.resetinc);

        //shared prefrerences
        s_preferences = new spref_manager(getContext());

        //Creation of the dbHelper
        dbHelper = new IncidenciaDBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        //spinner
        languaje = settings.findViewById(R.id.lang);
        final String[] lan = new String[]{getResources().getString(R.string.sel_lang),"Español", "Inglés"};
        // Initializing an ArrayAdapter
        final ArrayAdapter adapter = new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_spinner_dropdown_item,lan){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    //hacemos que el primer item no sea seleccionable
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView i = (TextView) view;
                if(position == 0){
                    i.setTextColor(Color.GRAY);
                }
                else {
                    i.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languaje.setPrompt(getResources().getString(R.string.in_type));
        languaje.setAdapter(adapter);
        //end

        btnsave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (languaje.getSelectedItem().toString() == lan[1]){
                    changeLocale("es");

                } else if (languaje.getSelectedItem().toString() == lan[2]){
                    changeLocale("en");
                }
                onrefresh();

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetapp();
            }
        });

        resetinc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                delall();
            }
        });


        // Inflate the layout for this fragment
        return settings;
    }

    //Change Locale
    public void changeLocale(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);//Set Selected Locale
        savelangDetails(lang);//Save the selected locale
        Locale.setDefault(myLocale);//set new locale as default
        Configuration config = new Configuration();//get Configuration
        config.locale = myLocale;//set config locale as selected locale
        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());//Update the config
    }

    private void savelangDetails(String lang) {
        new spref_manager(getContext()).savelangDetails(lang);
    }

    public void onrefresh(){
        Intent intent = getActivity().getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().overridePendingTransition(0, 0);
        getActivity().finish();

        getActivity().overridePendingTransition(0, 0);
        startActivity(intent);
    }
    
    public void delall() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        // set title
        alertDialogBuilder.setTitle(R.string.dialog_cln_tl);

        // set dialog message
        alertDialogBuilder
                .setMessage(R.string.dialog_mensaje)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_aceptar,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dbHelper.delete();
                        Toast.makeText(getContext(), getString(R.string.dialog_del_msg),
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.dialog_cancelar,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Toast.makeText(getContext(), getString(R.string.dialog_cln_msg),
                                Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
    public void resetapp() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        // set title
        alertDialogBuilder.setTitle(R.string.dialog_cln_tl);

        // set dialog message
        alertDialogBuilder
                .setMessage(R.string.dialog_reset)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_aceptar,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dbHelper.delete();
                        s_preferences.reset();
                        System.exit(2);
                    }
                })
                .setNegativeButton(R.string.dialog_cancelar,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Toast.makeText(getContext(), getString(R.string.dialog_cln_msg),
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