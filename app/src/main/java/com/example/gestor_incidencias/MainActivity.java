package com.example.gestor_incidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.gestor_incidencias.clases.spref_manager;
import com.example.gestor_incidencias.clases.usuario;
import com.example.gestor_incidencias.db.IncidenciaDBHelper;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    //declaracion de variables junto a su tipo
    Button logbtn;
    EditText loguser, logpass;
    private CheckBox checkBoxRememberMe;
    private spref_manager s_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s_preferences = new spref_manager(getApplicationContext());

        //Checkbox
        checkBoxRememberMe = (CheckBox) findViewById(R.id.checkBox);
        //si el usuario no esta loggeado
        if (!new spref_manager(this).isUserLogedOut()) {
            //el usuario y la contraseña estan guardados en preferences
            gotomenu();
        }

        //De declaracion de variables
        loguser = findViewById(R.id.loguser);
        logpass = findViewById(R.id.logpass);
        logbtn = findViewById(R.id.logbtn);

        //set hint
        loguser.setHint(R.string.login_user);
        logpass.setHint(R.string.login_pass);
        checkBoxRememberMe.setText(R.string.recordar_datos);
        logbtn.setText(R.string.bottom_login);

        //codigo del botton
        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario user = new usuario();
                if ((loguser.getText().toString().trim().equals(user.getuser())) && (logpass.getText().toString().trim().equals(user.getpasswd()))){
                    if (checkBoxRememberMe.isChecked())
                        saveLoginDetails(loguser.getText().toString().trim(), logpass.getText().toString().trim());
                    SharedPreferences sp = getSharedPreferences("LoginDetails" ,Context.MODE_PRIVATE);
                    s_preferences.getUser();
                    s_preferences.getPass();
                    gotomenu();
                }
                else{
                    Snackbar.make(v, R.string.login_failet, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }
    private void saveLoginDetails(String user, String password) {
        new spref_manager(this).saveLoginDetails(user, password);
    }

    public void gotomenu(){
        Intent intent = new Intent (MainActivity.this, iniciomenu.class);
        startActivity(intent);
    }

}