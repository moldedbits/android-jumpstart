package bdesir.raneh.salon.utils;

import android.content.Context;
import android.content.SharedPreferences;

import bdesir.raneh.salon.BaseApplication;

/**
 * Created by abhishek
 * on 18/03/15.
 */
public class LocalStorage {

    private final SharedPreferences preferences;

    private static final LocalStorage instance = new LocalStorage();

    public static LocalStorage getInstance() {
        return instance;
    }

    // TODO: 05/04/16 find a way to make this variable and app specific
    // TODO: 05/04/16 may be get application id somehow and append to this
    private static final String PREFS_NAME = "com.moldedbits.SharedPrefs";

    private LocalStorage() {
        preferences = BaseApplication.getInstance().getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
    }

    public void storeData(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void storeData(String key, long value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void storeData(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void storeData(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void storeData(String key, float value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return preferences.getString(key, null);
    }

    public float getFloat(String key) {
        return preferences.getFloat(key, 0.0f);
    }

    public long getLong(String key) {
        return preferences.getLong(key, 0);
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public void clear() {
        preferences.edit().clear().apply();
    }
}
