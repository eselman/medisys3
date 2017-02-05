package com.eselman.medisys.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by eselman on 04/02/2017.
 */

public class SessionHelper {
    private static SessionHelper instance;

    public SessionHelper() {

    }

    public static SessionHelper getInstance() {
        if (instance == null) {
            instance = new SessionHelper();
        }
        return instance;
    }

    public void saveSessionKey(String sessionKey, Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("SESSION_KEY", sessionKey.toString());
        editor.commit();
    }

    public String getSessionKey(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return sharedPref.getString("SESSION_KEY", null);
    }
}

