package me.eagzzycsl.netquality;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingSP {
    private final static String P_NAME = "me.eagzzycsl.netquality_preferences";

    private SharedPreferences sp;
    private static SettingSP instance;

    public SettingSP(Context context) {
        this.sp = context.getSharedPreferences(P_NAME, Context.MODE_PRIVATE);
    }

    public static SettingSP getInstance() {
        if (instance == null) {
            instance = new SettingSP(MyApplication.getContext());
        }
        return instance;
    }

    public boolean getAutoSave() {
        return sp.getBoolean("auto_save", false);
    }

    public boolean getTipWhenAutoSave() {
        return sp.getBoolean("tip_when_auto_save", false);
    }

}