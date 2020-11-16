package com.example.gestor_incidencias.clases;

import android.content.Context;
import android.content.SharedPreferences;

public class usuario {
    private String user = "admin";
    private String passwd = "admin";

    public void setuser(String titulo) {
        this.user = user;
    }

    public String getuser() {
        return user;
    }

    public void setpasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getpasswd() {
        return passwd;
    }

}
