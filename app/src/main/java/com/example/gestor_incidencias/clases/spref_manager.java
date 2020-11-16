package com.example.gestor_incidencias.clases;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Class for Shared Preference
 */
public class spref_manager {

    Context context;

    public spref_manager(Context context) {
        this.context = context;
    }

    public void saveLoginDetails(String user, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("User", user);
        editor.putString("Password", password);
        editor.commit();
    }

    public String getUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("User", "");
    }
    public String getPass() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Password", "");
    }

    public boolean isUserLogedOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isUserEmpty = sharedPreferences.getString("User", "").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password", "").isEmpty();
        return isUserEmpty || isPasswordEmpty;
    }
}