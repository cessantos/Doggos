package cess.com.br.doggos.models.cache;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferencesManager(Context context) {
        if (context != null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            editor = sharedPreferences.edit();
        }
    }

    public void setIntegerValue(String key, int value) {
        if (editor != null) {
            editor.putInt(key, value);
            editor.apply();
        }
    }

    public void setStringValue(String key, String value) {
        if (editor != null) {
            editor.putString(key, value);
            editor.apply();
        }
    }

    public void setBooleanValue(String key, Boolean value) {
        if (editor != null) {
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    public String getStringValue(String key, String defaultValue) {
        if (sharedPreferences != null)
            return sharedPreferences.getString(key, defaultValue);
        else
            return "";
    }

    public void setLongValue(String key, long value) {
        if (editor != null) {
            editor.putLong(key, value);
            editor.apply();
        }
    }

    public long getLongValue(String key, long defaultValue) {
        if (sharedPreferences != null) {
            return sharedPreferences.getLong(key, defaultValue);
        } else {
            return 0;
        }
    }

    public Boolean getBooleanValue(String key, Boolean defaultValue) {
        return sharedPreferences != null && sharedPreferences.getBoolean(key, defaultValue);
    }

    public int getIntegerValue(String key, int defaultValue) {
        if (sharedPreferences != null)
            return sharedPreferences.getInt(key, defaultValue);
        else
            return 0;
    }

    public void removeBooleanValue(String key) {
        if (editor != null) {
            editor.remove(key);
            editor.apply();
        }
    }

    public void removeStringValue(String key) {
        if (editor != null) {
            editor.remove(key);
            editor.apply();
        }
    }

    public void removeIntegerValue(String key) {
        if (editor != null) {
            editor.remove(key);
            editor.apply();
        }
    }
}
