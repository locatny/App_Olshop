package com.example.girlnyshop;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public AppPreferences(Context context) {
        pref = context.getSharedPreferences("appPref", Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setLogin(String email) {
        editor.putString("email", email);
        editor.putBoolean("isLogin", true);
        editor.apply();
    }

    public boolean isLogin() {
        return pref.getBoolean("isLogin", false);
    }

    public String getEmail() {
        return pref.getString("email", "");
    }

    public void removeLogin() {
        editor.clear();
        editor.apply();
    }
}
