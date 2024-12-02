package com.anganwadi.app.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    static final String KEY_AGE =
            "age";

    public static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(
                "age", Context.MODE_PRIVATE);
    }
    public static void setAge(Context context, String age) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_AGE, age);
        editor.apply();
    }

    public static String getAge(Context context) {
        return getSharedPreference(context).getString(KEY_AGE, "");
    }

    public static void clearData(Context context) {
        getSharedPreference(context).edit().clear().apply();
    }

}
