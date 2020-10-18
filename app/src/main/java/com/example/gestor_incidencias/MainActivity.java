package com.example.gestor_incidencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gestor_incidencias.clases.usuario;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    //declaracion de variables junto a su tipo
    Button logbtn;
    EditText loguser, logpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //De declaracion de variables
        loguser = findViewById(R.id.loguser);
        logpass = findViewById(R.id.logpass);
        logbtn = findViewById(R.id.logbtn);

        //codigo del botton
        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario user = new usuario();
                if ((loguser.getText().toString().trim().equals(user.getuser())) && (logpass.getText().toString().trim().equals(user.getpasswd()))){
                    gotomenu();
                }
                else{
                    Snackbar.make(v, "Usuario o contraseña incorrecto!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });
    }
    public void gotomenu(){
        Intent intent = new Intent (MainActivity.this, iniciomenu.class);
        startActivity(intent);
    }
}